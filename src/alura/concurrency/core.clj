(ns alura.concurrency.core
  (:use [clojure.pprint])
  (:require [alura.concurrency.model :as a.c.model]))

(let [my-hospital (a.c.model/create-hospital)]
  (pprint my-hospital))