(ns nyc.jeffparker.jackies-theme.config
  (:require [com.platypub.themes.default.config :refer [config]]))



(def site-fields
  [:com.platypub.site/description
   :com.platypub.site/image
   :com.platypub.site/redirects
   :com.platypub.site/author-name
   :com.platypub.site/author-url
   :com.platypub.site/author-image
   :com.platypub.site/embed-html])

(def event-fields
  {:nyc.jeffparker.jackies-theme.event/slug     {:label "Slug"
                                                 :default [:slugify :com.platypub.post/title]}
   :nyc.jeffparker.jackies-theme.event/event-id {:label "Event id"}
   :nyc.jeffparker.jackies-theme.event/source   {:label "Event source"}
   :nyc.jeffparker.jackies-theme.event/location {:label "Location"}
   :nyc.jeffparker.jackies-theme.event/dt-start {:label "Start datetime"
                                                 :type :instant}
   :nyc.jeffparker.jackies-theme.event/dt-end   {:label "End datetime"
                                                 :type :instant}
   :nyc.jeffparker.jackies-theme.event/status   {:label "Status"}})

(def events 
  {:key :events
   :label "Event"
   :slug "events"
   :query [:nyc.jeffparker.jackies-theme.event/slug]
   :fields [:com.platypub.post/title
            :nyc.jeffparker.jackies-theme.event/slug
            :com.platypub.post/draft
            :nyc.jeffparker.jackies-theme.event/location
            :com.platypub.post/published-at
            :nyc.jeffparker.jackies-theme.event/dt-start
            :nyc.jeffparker.jackies-theme.event/dt-end
            :com.platypub.post/tags
            :com.platypub.post/description
            :com.platypub.post/image
            :com.platypub.post/canonical
            :nyc.jeffparker.jackies-theme.event/event-id
            :nyc.jeffparker.jackies-theme.event/status
            :com.platypub.post/html]
   :sendable false
   :render/label :com.platypub.post/title
   :render/sections [{:label "Drafts"
                      :match [[:com.platypub.post/draft true]]
                      :order-by [[:com.platypub.post/title :desc]]
                      :show [:com.platypub.post/tags]}
                     {:label "Published"
                      :match [:not [[:com.platypub.post/draft true]]]
                      :order-by [[:com.platypub.post/published-at :desc]]
                      :show [:com.platypub.post/published-at
                             :com.platypub.post/tags]}]})

(defn -main []
  (let [items (->> config :items (conj events))
        fields (->> config :fields (merge event-fields))]
    (-> config
        (assoc :site-fields site-fields
               :fields fields
               :items items)
        prn)))