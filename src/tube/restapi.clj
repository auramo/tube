(ns tube.restapi
  (:require [clojure.data.json :as json])
  (:use [ring.util.response :only [response content-type]]))

(def ok-response {:status "ok"})

(defn- wrap-resp [response-data]
  (content-type (response (json/write-str response-data)) "application/json"))

(defn parameter-update [state update]
  (println "Got data:")
  (println update)
  (wrap-resp ok-response))
