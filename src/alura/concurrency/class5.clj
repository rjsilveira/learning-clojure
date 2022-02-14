(ns alura.concurrency.class5
  (:use [clojure pprint])
  (:require [alura.concurrency.model :as a.c.model]
            [alura.concurrency.concurrency_logic :as a.c.concurrency_logic]))

(defn arrive-in-hospital!
  "Coming in function"
  [my-hospital person]
  (swap! my-hospital a.c.concurrency_logic/coming-in :waiting-room person))

(defn transfer!
  "Transfer patient"
  [hospital from to]
  (swap! hospital a.c.concurrency_logic/transfer from to))

(defn populate-my-hospital!
  "Populate my hospital"
  [my-hospital]
  (let [patients ["Miranda" "Rogério" "Igor" "Diego" "Reinaldo"]]
    (doseq [patient patients]
      (arrive-in-hospital! my-hospital patient))))

(defn one-day-at-hospital
  "One day at hospital"
  []
  (let [my-hospital (atom (a.c.model/create-hospital))]
    (populate-my-hospital! my-hospital)
    (pprint my-hospital)
    (transfer! my-hospital :waiting-room :lab-01)
    (pprint my-hospital)
    (transfer! my-hospital :waiting-room :lab-02)
    (pprint my-hospital)
    (transfer! my-hospital :waiting-room :lab-02)
    (pprint my-hospital)
    (transfer! my-hospital :lab-02 :lab-03)
    (pprint my-hospital)))

(one-day-at-hospital)