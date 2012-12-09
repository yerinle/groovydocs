(ns groovydocs.core-test
  (:use clojure.test
        groovydocs.core))

(def groovy-doc-url "http://groovy.codehaus.org/groovy-jdk/")
(def grails-doc-url "http://grails.org/doc/latest/api/")

(deftest map-size
  (testing "Groovy/Grails docs map size"
    (is (= (count @*remote-groovydocs*) 6))))

(deftest groovy-jdk-keys
  (testing "Groovy JDK docs map contains right keys"
    (is (contains? @*remote-groovydocs* "java."))
    (is (contains? @*remote-groovydocs* "javax."))))

(deftest groovy-jdk-values
  (testing "Groovy JDK docs map contains right values"
    (is (= groovy-doc-url (get @*remote-groovydocs* "java.")))
    (is (= groovy-doc-url (get @*remote-groovydocs* "javax.")))))


(deftest grails-doc-keys
  (testing "Grails docs map contains right keys"
    (is (contains? @*remote-groovydocs* "grails."))
    (is (contains? @*remote-groovydocs* "org.codehaus.groovy.grails."))
    (is (contains? @*remote-groovydocs* "org.grails.plugins.tomcat."))
    (is (contains? @*remote-groovydocs* "org.slf4j.impl."))))

(deftest grails-doc-values
  (testing "Groovy JDK docs map contains right values"
    (is (= grails-doc-url (get @*remote-groovydocs* "grails.")))
    (is (= grails-doc-url (get @*remote-groovydocs* "org.codehaus.groovy.grails.")))
    (is (= grails-doc-url (get @*remote-groovydocs* "org.grails.plugins.tomcat.")))
    (is (= grails-doc-url (get @*remote-groovydocs* "org.slf4j.impl.")))))
