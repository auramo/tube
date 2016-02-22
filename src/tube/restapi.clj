(ns tube.restapi
  (:require [clojure.data.json :as json])
  (:use [ring.util.response :only [response content-type]]))

(def ok-response {:status "ok"})

(defn- wrap-resp [response-data]
  (content-type (response (json/write-str response-data)) "application/json"))

(defn radiator-state [state]
  (wrap-resp @state))

(defn parameter-update [state update-raw]
  (println "Got data:")
  (println update-raw)
  (let [update-data (json/read-str update-raw)
        new-parameters (map #(if (contains? update-data (:id %)) (assoc % :value (get update-data (:id %)) :updated (System/currentTimeMillis)) %) (:parameters @state))]
    (println "parameters after update" new-parameters)
    (swap! state assoc :parameters new-parameters))
  (wrap-resp ok-response))
