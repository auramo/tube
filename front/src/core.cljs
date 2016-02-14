(ns tube
  (:require [yolk.bacon :as bacon]))

(enable-console-print!)

(defn init []
  (println "yeah"))

(def input-action (bacon/bus))

(defn input [evt]
  (println "plain input" (.-value (.-target evt)))
  (bacon/push input-action (.-value (.-target evt))))

(init)

(bacon/on-value input-action #(println "bus got " %1))
