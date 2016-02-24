(defproject tube-front "1.2.3"
  :clean-targets ^{:protect false} ["web/js"]
  :plugins [[lein-cljsbuild "1.1.2"]]
  :dependencies [[org.clojure/clojurescript "1.7.228"]
                 [org.clojure/clojure "1.7.0"]
                 [cljs-ajax "0.5.3"]
                 [reagent "0.6.0-alpha"]]
  :cljsbuild {
              :builds {
                       :dev
                       {:source-paths ["src"]
                        :compiler {:output-to "web/js/main.js"
                                   :output-dir "web/js/out"
                                   :main tube.core
                                   :hashbang false
                                   :asset-path "js/out"
                                   :source-map true
                                   :optimizations :none }}
                       :prod
                       {:source-paths ["src"]
                        :compiler {:output-to "web/js/main.js"  ; default: target/cljsbuild-main.js
                                   :output-dir "web/js"
                                   :source-map "web/js/main.js.map"
                                   :optimizations :whitespace }}}})
