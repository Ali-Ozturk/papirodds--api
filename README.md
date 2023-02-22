# Papirodds

## TODO's
- [x] Add maps to Match object and HLTV-API
- [x] Add avatar logos to player object
- [x] Modify HLTV api to Be able to get details from on-going or not yet started matches

**Endpoints**
- [x] Get Sorted Scores for current contest
- [x] Validate user using username + validation code
- [x] POST Match, team, players, thresholds entity
- [x] GET active matches
- [x] GET matches from HLTV
- [ ] Allow edit match odds
- [ ] Add odds per map
- [x] HLTV-API: Get match details show bo3/bo1.
- [x] Add enum to entity as well (enum ordinal)
- [x] Consider saving in memory last fetch of all hltv Api requests or limit somehow. Store 10 min ish


## Scheduler

* Run Every 45 minutes
* For every active match Id fetch match details
* If finished: check user odds compare to results, Update scores, set validated,
    * Only check matches that are to be played within the before/after 2 hours


Future? Update Per map?


