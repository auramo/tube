(ns tube.radiator-display)

(defn radiator [state]
  [:div
   [:div.header (str (:title @state) " radiator")]
   [:div.parameters
    [:div.parameter-box.gloss
     [:div.warning "Delayed"]
     [:div.parameter 77]
     [:div.parameter-name "Tilaukset"]]]])
