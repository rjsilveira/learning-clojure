(ns alura.concurrency.collections
  (:use [clojure.pprint])
  (:import (clojure.lang PersistentQueue)))

(defn my-vector
  "My vector"
  []
  (let [waiting [11 22]]
    (println waiting)
    (println (conj waiting 33))
    (println (pop waiting))))

(my-vector)


(defn my-list
  "My list"
  []
  (let [waiting '(11 22)]
    (println waiting)
    (println (conj waiting 33))
    (println (pop waiting))))

(my-list)

(defn my-set
  "My set"
  []
  (let [waiting #{11 22}]
    (println waiting)
    (println (conj waiting 33))))

(my-set)

(defn queue
  "My queue"
  []
  (let [waiting (conj PersistentQueue/EMPTY "11" "22")]
    (println "queue")
    (println waiting)
    (println (seq waiting))
    (println (seq (conj waiting "33")))
    (println (seq (pop waiting)))
    (println (peek waiting))
    (pprint waiting)))

(queue)