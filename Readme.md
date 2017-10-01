# Project 2 - *NewsArticleSearch*

**NewsArticleSearch** is an android app that allows a user to search for articles on web using simple filters. The app utilizes [New York Times Search API](http://developer.nytimes.com/docs/read/article_search_api_v2).

Time spent: **51** hours spent in total

## User Stories

The following **required** functionality is completed:

* [ ] User can **search for news article** by specifying a query and launching a search. Search displays a grid of image results from the New York Times Search API.
* [ ] User can click on "settings" which allows selection of **advanced search options** to filter results
* [ ] User can configure advanced search filters such as:
  * [ ] Begin Date (using a date picker)
  * [ ] News desk values (Arts, Fashion & Style, Sports)
  * [ ] Sort order (oldest or newest)
* [ ] Subsequent searches have any filters applied to the search results
* [ ] User can tap on any article in results to view the contents in an embedded browser.
* [ ] User can **scroll down to see more articles**. The maximum number of articles is limited by the API search.

The following **optional** features are implemented:

* [ ] Implements robust error handling, [check if internet is available](http://guides.codepath.com/android/Sending-and-Managing-Network-Requests#checking-for-network-connectivity), handle error cases, network failures
* [ ] Used the **ActionBar SearchView** or custom layout as the query box instead of an EditText
* [ ] User can **share an article link** to their friends or email it to themselves
* [ ] Replaced Filter Settings Activity with a lightweight modal overlay

The following **bonus** features are implemented:

* [ ] Use the [RecyclerView](http://guides.codepath.com/android/Using-the-RecyclerView) with the `StaggeredGridLayoutManager` to display improve the grid of image results
* [ ] Use Parcelable instead of Serializable using the popular [Parceler library](http://guides.codepath.com/android/Using-Parceler).
* [ ] Replace all icon drawables and other static image assets with [vector drawables](http://guides.codepath.com/android/Drawables#vector-drawables) where appropriate.
* [ ] Replace Picasso with [Glide](http://inthecheesefactory.com/blog/get-to-know-glide-recommended-by-google/en) for more efficient image rendering.
* [ ] Leverages the popular [GSON library](http://guides.codepath.com/android/Using-Android-Async-Http-Client#decoding-with-gson-library) to streamline the parsing of JSON data.
* [ ] Replace the embedded `WebView` with [Chrome Custom Tabs](http://guides.codepath.com/android/Chrome-Custom-Tabs) using a custom action button for sharing. (_**2 points**_)

The following **additional** features are implemented:

* [ ] used MVP architecture pattern

## Video Walkthrough

Here's a walkthrough of implemented user stories:
<a href='http://i.imgur.com/Eo0mNao.gifv'>Click here to Watch Video Walkthrough</a>
</br>
<img src='http://i.imgur.com/Eo0mNao.gifv' title='Video Walkthrough' width='' alt='Video Walkthrough' />

GIF created with [LiceCap](http://www.cockos.com/licecap/).

## Notes

Describe any challenges encountered while building the app.
* Url response was large for network connection.
* Network response error was not resolved because request was based on *http* instead of *https*

## Open-source libraries used

- [Androd Okhttp](https://github.com/square/okhttp) - Simple asynchronous HTTP requests with JSON parsing
- [Glide](https://github.com/bumptech/glide) - Image loading and caching library for Android.
- [Gson](https://github.com/google/gson) - To convert between JSON strings and Java objects.
- [ButterKnife](http://jakewharton.github.io/butterknife/) - View "injection" library for Android

## License

    Copyright [yyyy] [Aelafseged Tewodros Amdemikael]

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

        http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
