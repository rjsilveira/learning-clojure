(ns alura.concurrency.class3
  (:use [clojure pprint])
  (:require [alura.concurrency.model :as a.c.model]
            [alura.concurrency.concurrency_logic :as a.c.concurrency_logic]))

(defn my-atom-function
  "My atom function"
  []
  (let [my-hospital (atom {:waiting-room a.c.model/empty-queue})]
    (pprint my-hospital)
    (pprint @my-hospital)

    ;; Dont change the map value
    (pprint (assoc @my-hospital :lab-01 a.c.model/empty-queue))
    (pprint @my-hospital)

    ;; This change de value of my-hospital
    (swap! my-hospital assoc :lab-01 a.c.model/empty-queue)
    (pprint @my-hospital)

    (swap! my-hospital assoc :lab-02 a.c.model/empty-queue)
    (pprint @my-hospital)

    (swap! my-hospital update :waiting-room conj "Renata")
    (pprint @my-hospital)))

(my-atom-function)