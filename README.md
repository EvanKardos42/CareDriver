# CareDriver
take home Interview project for HopSkipDrive

third party libraries:
  *Retrofit
  *GSON
  
Link to APK file
 https://github.com/EvanKardos42/CareDriver/blob/master/app-debug.apk
 https://github.com/EvanKardos42/CareDriver/blob/master/output-metadata.json
 
 notes:
    $ my application takes into account that the rides come in order by date and time.
    To seperate the rides into their respecive days and keep there order, I use a 
    likedlistHashmap to seperate the rides by date as the key and use an arraylist to keep
    there proper order of time. 
    You will see this in the RideCollection class, which is what I also use to create the headers
    for the recycleview.
    $ One of the Ordered rides has 3 different location but 2 have the same geo location so only 2 way points will appear on the map.
    
  
  Improvements:
   $ I didnt have time but I would add a back button in the ride details fragment using a costum toolbar layout,
   a pull down feature so user can make another call to fetch the ordered rides if there are any new ones, and 
   use styles to make the text more appealing to the user.
   
    
  
