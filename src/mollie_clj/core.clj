(ns mollie-clj.core
 (:require [mollie-clj.config :refer [api-key]]))

(defn set-api-key! 
  [k]
  (reset! api-key k))

(def base-url "https://api.mollie.nl/v1")
