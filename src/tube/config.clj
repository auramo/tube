(ns tube.config
  (:require [clojure.data.json :as json]))

(defn read-config [config-file]
  (println (str "Reading config from " config-file))
  (try
    (json/read-str (slurp config-file) :key-fn keyword)
    (catch java.io.FileNotFoundException e
      (println (str "Config file " config-file " not found"))
      (System/exit 2))))
