(ns mattsum.simple-example.core
  (:require [reagent.core :as r :refer [atom]]))

#_(enable-console-print!)

;; we need set! for advanced compilation

(set! js/React (js/require "react-native/Libraries/react-native/react-native.js"))
(defonce react (js/require "react-native/Libraries/react-native/react-native.js"))

(def view (r/adapt-react-class (.-View react)))
(def text (r/adapt-react-class (.-Text react)))

(def x (atom 0))

(defn root-view []
  [view
   [text {:style {:margin-top 22
                  :margin-left 8
                  :font-size 48
                  :color "#00a"}}
    "x=" @x]])

(defn ^:export main []
  (enable-console-print!)
  (js/console.log "MAIN")
  (doseq [k ["SimpleExampleApp" "main"]]
    (.registerComponent (.-AppRegistry react)
                        k
                        #(r/reactify-component root-view))))

(defn on-js-reload []
  (js/console.log "JS RELOADING")
  (r/render [root-view] 1))
