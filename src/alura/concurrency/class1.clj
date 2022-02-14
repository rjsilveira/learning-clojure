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
  (pprint hospital)
  (def hospital (a.c.concurrency_logic/coming-in hospital :lab-01 "Fernanda"))
  (def hospital (a.c.concurrency_logic/coming-in hospital :lab-03 "Larissa"))
  (pprint hospital)
  (def hospital (a.c.concurrency_logic/attend hospital :waiting-room))
  (pprint hospital))

(day-one)