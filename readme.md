This bare-bones stackoverflow client 
serves as a playground for proper unit testing
of Android system components


---


Current App Flow:

Static methods in WebIntentService serve as facade layer to UI
WebIntentService passes results of WebAPI calls to WebDataStorage
WebDataStorage notifies Activity of changes to initiate a UI update


---

TODOs

Better dependency injections throughout
More robust testing of web layer
Consider custom Service rather than IntentService
Parse JSON responses into real UI

