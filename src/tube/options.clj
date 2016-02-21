(ns tube.options
  (:require [clojure.data.json :as json])
  (:use clojopts.core))

(defn cmd-line-options []
  (clojopts "tube"
            *command-line-args*
            (optional-arg server-port p "Server port" :parse #(int %))
            (optional-arg config-file f name "Config file")))

(defn get-options []
  (try
    (let [raw-options (cmd-line-options)
          defaults {:server-port 8200 :config-file "tube-config.json"}]
      (merge defaults raw-options))
    (catch IllegalArgumentException e
      (System/exit 1))))
