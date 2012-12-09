(ns groovydocs.core-test
  (:use clojure.test
        groovydocs.core))

(deftest map-size
  (testing "Groovy docs map size"
    (is (= (count @*remote-groovydocs*) 2))))

(deftest groovy-jdk-keys
  (testing "Groovy JDK docs map contains right keys"
    (is (contains? @*remote-groovydocs* "java."))
    (is (contains? @*remote-groovydocs* "javax."))))

(deftest groovy-jdk-values
  (testing "Groovy JDK docs map contains right values"
    (is (= "http://groovy.codehaus.org/groovy-jdk/" (get @*remote-groovydocs* "java.")))
    (is (= "http://groovy.codehaus.org/groovy-jdk/" (get @*remote-groovydocs* "javax.")))))
