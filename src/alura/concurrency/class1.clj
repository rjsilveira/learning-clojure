(ns alura.concurrency.class1
  (:use [clojure.pprint])
  (:require [alura.concurrency.model :as a.c.model]
            [alura.concurrency.concurrency_logic :as a.c.concurrency_logic]))

(defn day-one
  "Day one"
  []
  (def hospital (a.c.model/create-hospital))
  (def hospital (a.c.concurrency_logic/coming-in hospital :waiting-room "Fernanda"))
  (def hospital (a.c.concurrency_logic/coming-in hospital :waiting-room "Larissa"))
  (def hospital (a.c.concurrency_logic/coming-in hospital :waiting-room "Riquelme"))
  (def hospital (a.c.concurrency_logic/coming-in hospital :waiting-room "Adriano"))
  (pprint hospital)
  (def hospital (a.c.concurrency_logic/coming-in hospital :lab-01 "Fernanda"))
  (def hospital (a.c.concurrency_logic/coming-in hospital :lab-03 "Larissa"))
  (pprint hospital)
  (def hospital (a.c.concurrency_logic/attend hospital :waiting-room))
  (pprint hospital))

(day-one)

(defn coming-in-function
  "Coming in function"
  [person]
  (def hospital (a.c.concurrency_logic/coming-in hospital :waiting-room person)))

(defn coming-in-function-with-pause
  "Coming in function"
  [person]
  (Thread/sleep (* (rand) 2000))
  (def hospital (a.c.concurrency_logic/coming-in hospital :waiting-room person)))

(defn parallel
  "Parallel"
  []
  (def hospital (a.c.model/create-hospital))
  (.start (Thread. (fn [] (coming-in-function-with-pause "Miranda"))))
  (.start (Thread. (fn [] (coming-in-function-with-pause "Rogério"))))
  (.start (Thread. (fn [] (coming-in-function-with-pause "Igor"))))
  (.start (Thread. (fn [] (coming-in-function-with-pause "Diego"))))
  (.start (Thread. (fn [] (coming-in-function-with-pause "Reinaldo"))))
  (.start (Thread. (fn [] (coming-in-function-with-pause "Luan"))))
  (.start (Thread. (fn [] (coming-in-function-with-pause "Gabriel"))))
  (.start (Thread. (fn [] (coming-in-function-with-pause "Marcos"))))
  (.start (Thread. (fn [] (coming-in-function-with-pause "Éder"))))
  (.start (Thread. (fn [] (coming-in-function-with-pause "Pablo"))))
  (.start (Thread. (fn [] (coming-in-function-with-pause "Luciano"))))
  (.start (Thread. (fn [] (coming-in-function-with-pause "Rafael"))))
  (.start (Thread. (fn [] (Thread/sleep 4000) (pprint hospital)))))

(parallel)