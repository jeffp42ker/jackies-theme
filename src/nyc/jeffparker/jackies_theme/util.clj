(ns nyc.jeffparker.jackies-theme.util
  (:require
   [com.platypub.themes.common
    :as common
    :refer [render! without-ns]
    :exclude [derive-opts]]))

(defn events! [{:keys [events] :as opts} render-event]
  (doseq [event events
          :let [path (str "/e/" (:slug event) "/")]]
    (render! path
             "<!DOCTYPE html>"
             (render-event (assoc opts :base/path path :event event)))))

(defn derive-opts [{:keys [site item lists events posts pages] :as opts}]
  (let [events (->> events
                    (map without-ns)
                    (remove :draft)
                    (map #(update % :tags set))
                    (sort-by :published-at #(compare %2 %1)))
        posts (->> posts
                   (map without-ns)
                   (remove :draft)
                   (map #(update % :tags set))
                   (sort-by :published-at #(compare %2 %1)))
        pages (->> pages
                   (map without-ns)
                   (remove :draft)
                   (map #(update % :tags set)))
        welcome (->> posts
                     (filter #((:tags %) "welcome"))
                     first)
        posts (->> posts
                   (remove #((:tags %) "welcome")))]
    (assoc opts
           :site (without-ns site)
           :post (-> item
                     without-ns
                     (update :tags set))
           :events events
           :posts posts
           :pages pages
           :list (without-ns (first lists))
           :welcome welcome)))
