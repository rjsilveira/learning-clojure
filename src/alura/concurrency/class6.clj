(ns alura.concurrency.class6
  (:use [clojure pprint])
  (:require [alura.concurrency.model :as a.c.model]))

(defn full?
  "The queue is full?"
  [hospital]
  (-> hospital
      count,,,
      (<,,, 5)))

(defn arrives
  "Put people in waiting room when arrives in hospital"
  [queue patient]
  (if (full? queue)
    (conj queue patient)
    (throw (ex-info "Queue is full!" {:patient patient}))))

(defn arrives!
  "Put people in waiting room when arrives in hospital"
  [hospital patient]
  (let [queue (get hospital :waiting-room)]
    (ref-set queue (arrives @queue patient))))

(defn arrives!
  "Put people in waiting room when arrives in hospital"
  [hospital patient]
  (let [queue (get hospital :waiting-room)]
    (alter queue arrives patient)))

(defn one-day
  "One day at hospital"
  []
  (let [hospital {:waiting-room (ref a.c.model/empty-queue)
                  :lab-01       (ref a.c.model/empty-queue)
                  :lab-02       (ref a.c.model/empty-queue)
                  :lab-03       (ref a.c.model/empty-queue)}]
    (dosync
      (arrives! hospital "Rogério")
      (arrives! hospital "Igor")
      (arrives! hospital "Diego")
      (arrives! hospital "Gabriel")
      (arrives! hospital "Luciano"))
    (pprint hospital)))

(defn async-arrives!
  "Put people in waiting room when arrives in hospital async"
  [hospital patient]
  (future
    (Thread/sleep (rand 5000))
    (dosync
      (println "Trying sync code" patient)
      (arrives! hospital patient))))

;; (one-day)

(defn one-day-async
  "One day at hospital"
  []
  (let [hospital {:waiting-room (ref a.c.model/empty-queue)
                  :lab-01       (ref a.c.model/empty-queue)
                  :lab-02       (ref a.c.model/empty-queue)
                  :lab-03       (ref a.c.model/empty-queue)}
        futures (mapv #(async-arrives! hospital %) (range 10))]
    (future
      (Thread/sleep 1000)
      (pprint hospital)
      (pprint futures))))

(one-day-async)