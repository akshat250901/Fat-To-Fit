#Fat To Fit: Diet Managing System

## What will my application do?
My application which will be a **Diet Tracker web app** which will
help users to keep a track of their food and water intake throughout 
the day.  

## Who will use it?
This app can be used by anyone who wants to keep a track of their food
and water intake on daily basis. 

## Why is this project of interest to you?
I find this project interesting because i and my friends are really
into fitness and healthy eating habits,  so I think this app will make our work easier
to maintain a healthy life.

## User Stories

**As a user**
- I want to be able to *add food on daily basis* into my app to track my consumption

- I want to be able to *track my food and calorie consumption* on a particular date

- I want to be able to *log my water* on daily basis 

- I want to be able to *track my water consumption* on a particular date

- I want to be able to *set a calorie goal* and should get a message if I exceed my calorie goal

- I want to be able to *save my food and water consumption* of a particular day

- I want to be able to *retrieve my food and water consumption* of a particular day 
  after restarting the application and still add food or water

##Phase 4: Task 2
To complete this task I have completed the following java language construct:
*Test and design a class in your model package that is robust.  You must have at least one method that throws a 
checked exception.  You must have one test for the case where the exception is expected and another where the 
exception is not expected.*

**I have made the Water class robust which now throws 2 exceptions i.e. NegativeWaterException and 
IncorrectDateFormatException and none of the methods have a requires clause**

##Phase 4: Task 3
- I would have made an abstract class which was extended by Water and Food class because they have some similar methods.

- I would have made Food class more robust by removing the requires clauses

- I would have made an abstract class which would have been extended by TotalWaterConsumptionGUI and 
TotalFoodConsumptionGUI

- I would have made an abstract class which would have been extended by WaterConsumptionOnDateGUI and 
  FoodConsumptionOnDateGUI

- I would have made an abstract class which would have been extended by AddingWaterGUI and 
  AddingFoodGUI 

- I would have tried to reduce coupling between MonitorConsumption Class and Food class.

- I would have tried making GUI classes more cohesive by making an additional class for the Button used for going back
because it's being used in most of my classes.