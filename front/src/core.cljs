(ns tube.core
  (:require [reagent.core :as reagent]
            [ajax.core :refer [GET]]
            [tube.radiator-display :refer [radiator]]))

(enable-console-print!)

(defn fetch-state [state]
  (GET "/api/radiator-state" {:handler #(println "response" %)
                                :error-handler #(println "error" %)}))

(defn app-fn [state]
  [radiator state])

(defn app-m [state] (with-meta #(app-fn state) {:component-did-mount #(fetch-state state) }))

(defn init []
  (let [state (atom {:title "Tube"})]
    (reagent/render [(app-m state)] (.getElementById js/document "content"))))

(init)
