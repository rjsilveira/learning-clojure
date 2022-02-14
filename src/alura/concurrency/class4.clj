(ns alura.concurrency.class4
  (:use [clojure pprint])
  (:require [alura.concurrency.model :as a.c.model]
            [alura.concurrency.concurrency_logic :as a.c.concurrency_logic]))

(defn coming-in-function-with-pause!
  "Coming in function"
  [my-hospital person]
  (Thread/sleep (* (rand) 2000))
  (swap! my-hospital a.c.concurrency_logic/coming-in :waiting-room person))

(defn parallel
  "Parallel"
  []
  (def hospital (a.c.model/create-hospital))
  (.start (Thread. (fn [] (coming-in-function-with-pause! hospital "Miranda"))))
  (.start (Thread. (fn [] (coming-in-function-with-pause! hospital "Rogério"))))
  (.start (Thread. (fn [] (coming-in-function-with-pause! hospital "Igor"))))
  (.start (Thread. (fn [] (coming-in-function-with-pause! hospital "Diego"))))
  (.start (Thread. (fn [] (coming-in-function-with-pause! hospital "Reinaldo"))))
  (.start (Thread. (fn [] (coming-in-function-with-pause! hospital "Luan"))))
  (.start (Thread. (fn [] (coming-in-function-with-pause! hospital "Gabriel"))))
  (.start (Thread. (fn [] (coming-in-function-with-pause! hospital "Marcos"))))
  (.start (Thread. (fn [] (coming-in-function-with-pause! hospital "Éder"))))
  (.start (Thread. (fn [] (coming-in-function-with-pause! hospital "Pablo"))))
  (.start (Thread. (fn [] (coming-in-function-with-pause! hospital "Luciano"))))
  (.start (Thread. (fn [] (coming-in-function-with-pause! hospital "Rafael"))))
  (.start (Thread. (fn [] (Thread/sleep 4000) (pprint hospital)))))

(defn my-thread-start-function
  "My thread start function"
  [my-hospital person]
  (.start (Thread. #(coming-in-function-with-pause! my-hospital person))))

(defn my-parallel-function
  "My parallel function"
  []
  (let [my-hospital (atom (a.c.model/create-hospital))
        persons ["Miranda" "Rogério" "Igor" "Diego" "Reinaldo" "Luan" "Gabriel" "Marcos" "Éder" "Pablo" "Luciano" "Rafael"]]
    (doseq [person persons] (my-thread-start-function my-hospital person))
    (.start (Thread. (fn [] (Thread/sleep 4000) (pprint my-hospital))))))

(my-parallel-function)

(defn my-dotimes
  "My do times function"
  []
  (dotimes [number 10] (pprint number)))

(my-dotimes)