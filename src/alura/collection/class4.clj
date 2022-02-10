(ns alura.collection.class4
  (:require [alura.collection.database :as a.c.database]
            [alura.collection.logic :as a.c.logic]))

(defn order
  "Return resume requests ordered"
  [resume]
  (->> resume
       (sort-by :total-value)
       reverse))

(let [resume (a.c.logic/resume-by-user (a.c.database/find-all))]
  (println "f=resume resume=" resume)
  (println "f=resume ordered-resume" (order resume))
  (println "f=resume ordered-resume-desc" (reverse (sort-by :total-value resume)))
  (println "f=resume ordered-resume-id" (sort-by :user resume))
  (println "f=resume take-two" (take 2 resume))
  (println "f=resume filter" (filter #(> (:total-value %) 500) resume))
  (println "f=resume some" (some #(> (:total-value %) 500) resume)))

