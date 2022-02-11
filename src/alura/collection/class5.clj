(ns alura.collection.class5
  (:require [alura.collection.database :as a.c.database]
            [alura.collection.logic :as a.c.logic]))

(defn expensive?
  "Is request expensive"
  [request]
  (if (> (:total-value request) 500)
    request))

(let [request (a.c.database/find-all)
      resume (a.c.logic/resume-by-user request)]
  (println "f=request-and-resume keep" (keep expensive? resume)))


(defn my-filter
  "Rewrite filter function"
  [arg]
  (println "f=my-function" arg)
  arg)

(defn my-another-filter
  "Rewrite filter function"
  [arg]
  (println "f=my-another-function" arg)
  arg)

(->> (range 150)
     (filter my-filter)
     (filter my-another-filter)
     println)