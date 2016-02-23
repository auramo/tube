(ns tube.core
  (:require [reagent.core :as reagent]
            [ajax.core :refer [GET]]))

(enable-console-print!)

(defn app-fn [state]
  [:div "tube"])

(defn fetch-state [state]
  (GET "/api/radiator-state" {:handler #(println "response" %)
                                :error-handler #(println "error" %)}))

(defn app-m [state] (with-meta app-fn {:component-did-mount #(fetch-state state) }))

(defn init []
  (let [state (atom {:x "y"})]
    (reagent/render [(app-m state)] (.-body js/document))
    ))

(init)
