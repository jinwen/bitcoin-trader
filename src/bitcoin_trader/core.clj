(ns bitcoin-trader.core
  (:require [org.httpkit.client :as http]
            [clojure.string :as str]))

(defn parse-price
  [resp-body]
  (let [last-line (last (str/split-lines resp-body))]
    (nth (str/split last-line #",") 1)))

(defn price
  []
  (let [{:keys [status headers body error] :as resp} @(http/get "http://market.huobi.com/staticmarket/td.html")]
    (if error
      (println "Failed, exception: " error)
      (parse-price body))))

(defn print-price
  []
  (println (price)))

(defn -main
  []
  (print-price))
