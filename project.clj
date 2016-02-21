(defproject tube "0.1.0-SNAPSHOT"
  :description "Radiator for monitoring key things of systems"
  :min-lein-version "2.5.3"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.8.0"]
                 [http-kit "2.1.18"]
                 [javax.servlet/servlet-api "2.5"]
                 [compojure "1.4.0"]
                 [org.clojure/data.json "0.2.6"]
                 [clojopts "0.3.5"]]
  :uberjar-name "tube.jar"
  :main ^:skip-aot tube.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}})
