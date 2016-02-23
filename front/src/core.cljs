(ns tube.core
  (:require [reagent.core :as reagent]))

(enable-console-print!)

(defn app [state]
  [:div "tube"])

(defn init []
  (reagent/render [app {:x "y"}] (.-body js/document)))

(init)
