(defproject tube-front "1.2.3"
  :clean-targets ^{:protect false} ["web/js"]
  :plugins [[lein-cljsbuild "1.1.2"]]
  :dependencies [[org.clojure/clojurescript "1.7.228"]
                 [org.clojure/clojure "1.7.0"]
                 [yolk "0.9.0"]]
  :cljsbuild {
    :builds [{
        ; The path to the top-level ClojureScript source directory:
        :source-paths ["src"]
        ; The standard ClojureScript compiler options:
        ; (See the ClojureScript compiler documentation for details.)
        :compiler {
                   :output-to "web/js/main.js"  ; default: target/cljsbuild-main.js
                   :output-dir "web/js"
                   :source-map "web/js/main.js.map"
                   :optimizations :whitespace }}]})
