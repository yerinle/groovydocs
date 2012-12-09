(defproject groovydocs "0.1.0-SNAPSHOT"
  :description "A Clojure repl helper to launch groovy/grails javadocs in the browser"
  :url "http://github.com/yerinle/groovydocs"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.4.0"]
                 [org.grails/grails-core "2.1.0"]]

  :repositories [["grailsCentral" "http://grails.org/plugins" ]
                 ["grailsMavenRepo" "http://repo.grails.org/grails/core"]])
