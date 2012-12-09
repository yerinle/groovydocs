; Adapted from clojure.lang.javadoc
(ns groovydocs.core
  ^{:doc "A repl helper to quickly open groovy docs."}
  (:use [clojure.java.browse :only (browse-url)])
  (:require [clojure.java.javadoc :as javadoc])
  (:import (java.io File)))

(def ^:dynamic *feeling-lucky-url* "http://www.google.com/search?btnI=I%27m%20Feeling%20Lucky&q=allinurl:")
(def ^:dynamic *feeling-lucky* true)

(def ^:dynamic *core-groovy-api* "http://groovy.codehaus.org/groovy-jdk/")
(def ^:dynamic *local-groovydocs* (ref (list)))

(def ^:dynamic *remote-groovydocs*
  (ref (sorted-map
         "java." *core-groovy-api*
         "javax." *core-groovy-api*)))

(defn add-local-groovydoc
  "Adds to the list of local groovydoc paths."
  {:added "0.1"}
  [path]
  (dosync (commute *local-groovydocs* conj path)))

(defn- groovy-url
  "Searches for a URL for the given class name.  Tries
  *local-groovydocs* first, then *remote-groovydocs*.  Returns a string."
  {:tag String,
   :added "0.1"}
  [^String classname]
  (let [file-path (.replace classname \. File/separatorChar)
        url-path (.replace classname \. \/)]
    (if-let [file ^File (first
      (filter #(.exists ^File %)
        (map #(File. (str %) (str file-path ".html"))
          @*local-groovydocs* )))]
      (-> file .toURI str)
      ;; If no local file, try remote URLs:
      (or (some (fn [[prefix url]]
                  (when (.startsWith classname prefix)
                    (str url url-path ".html")))
            @*remote-groovydocs*)
        ;; if *feeling-lucky* try a web search
        (when *feeling-lucky* (str *feeling-lucky-url* url-path ".html"))))))

(defn groovydoc
  "Opens a browser window displaying the javadoc for the argument.
  Tries *local-javadocs* first, then *remote-javadocs*."
  {:added "0.1"}
  [class-or-object]
  (let [^Class c (if (instance? Class class-or-object)
    class-or-object
    (class class-or-object))]
    (if-let [url (groovy-url (.getName c))]
      (browse-url url)
      (println "Could not find Javadoc for" c))))

