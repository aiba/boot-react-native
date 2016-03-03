(ns mattsum.simple-example.core
  (:require [reagent.core :as r :refer [atom]]))

#_(enable-console-print!)

;; we need set! for advanced compilation

(set! js/React (js/require "react-native/Libraries/react-native/react-native.js"))
(defonce react (js/require "react-native/Libraries/react-native/react-native.js"))

(def view (r/adapt-react-class (.-View react)))
(def text (r/adapt-react-class (.-Text react)))

(defn root-view []
  [view
   [text {:style {:margin-top 22
                  :margin-left 8
                  :font-size 48
                  :color "#00a"}}
    "Hello World."]])

(defn ^:export main []
  (enable-console-print!)
  (js/console.log "MAIN")
  (.registerComponent (.-AppRegistry react)
                      "SimpleExampleApp"
                      #(r/reactify-component root-view)))

(defn on-js-reload []
  (js/console.log "JS RELOADING")
  (r/render [root-view] 1))
