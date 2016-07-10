# Data
### Overview
*Information about current bills and debts is displayed. Included are:*

- *number of bills submitted by that user*
- *how many of those bills are unassigned*
- *total (currently) spent*
- *total (currently) owed*

*If there are no bills, "No data available" is displayed instead.*

**Preconditions:** -  
**Postconditions:** Account Overview is displayed. 
 
**Normal flow:** User logs in and lands on Account Overview.  
**Alternative flow:** User clicks `Back` - Button and returns to Account Overview   
**Error flow:** -

### Get Detailed Information
*User needs more data about current bills and opens the Detailed Information View. Included information:*

- *total number of bills submitted by the user (including archived ones)*
- *number of non-archived bills submitted by the user*
- *how many of those bills are unassigned*
- *total (currently) spent*
- *total (currently) owed*
- *number of items submitted (including archived ones)*
- *number of non-archived submitted items*
- *number of unassigned items (submitted by the user)*
- *how many times have debts been paid?*

**Preconditions:** User is logged in, Account Overview is displayed  
**Postconditions:** Detailed Information View is displayed. 
 
**Normal flow:** User clicks `Extras` and selects `View Details`. Detailed Information View opens.  
**Alternative flow:** -  
**Error flow:** -

---

### Leave Detailed Information
*A user wants to use other parts of the program or log out / leave the website. They close the Detailed Information View.*

**Preconditions:** User is logged in, Detailed Information View is displayed.  
**Postconditions:** Detailed Information is closed.   

**Normal flow:** User clicks `Back` - Button. Detailed Information View is closed.  
**Alternative flow:** 
User logs out / is logged out via timeout. Detailed Information View is closed and will not be displayed at the next login.  
**Error flow:** -


---

# Balance
### Clear Debts
*Users want to clear their current debts. They use the "Pay Debts" - Functionality to display their balance and archive currently assigned bills after the debt has been paid. Both users need to approve.*

**Preconditions:** User is logged in, Account Overview is displayed, there are unpaid depts, the second user clicks all necessary buttons as well.  
**Postconditions:** All assigned current bills are archived as they are no longer relevant. 
 
**Normal flow:** User clicks `Pay Debts` - Button. A new view opens and tells them how much to pay / how much money they are owed. When they are done, they click the `Done` - Button and have to wait for their partner to do the same. When both users are done, all currently assigned bills are archived. Users are told that the process was successfull and they get to choose between 2 Buttons, `Home` and `Archive`.  
**Alternative flow:** -  
**Error flow:** There are two error cases:

- One user clicks `Done`, but the other one does nothing or clicks `Pay Debts` and then `Back` instead of `Done`:  
No new bills can be added and no unassigned bills can be assigned until the process is either finished (because the second user clicks the correct buttons) or aborted (the first user clicks `Abort`).

- User clicks `Pay Debts`, but not `Done`:  
Nothing special happens. The user can either continue (`Done`) or return (`Back`)

---

# Archive
### View Archive
*A user wants get information about old (already settled) bills. They open the program's Archive View, which contains data about:*

- *total money settled*
- *number of archived bills submitted by the user*
- *total cleared debt*

*If a user has not settled any bills yet, the Archive View is replaced with a short instruction about settling bills.*

**Preconditions:** User is logged in, Account Overview is displayed  
**Postconditions:** Archive View (of currently logged in user) is displayed.  

**Normal flow:** User clicks `Extras` - Button and selects `Archive`. The Archive View is opened.  
**Alternative flow:** Users clear current debts. When they are notified that the process was successful, they click `Archive`.  
**Error flow:** -

---

### Leave Archive
*A user wants to use other parts of the program or log out / leave the website. They close the Archive View.*

**Preconditions:** User is logged in, Archive View is displayed.  
**Postconditions:** Archive View is closed.   

**Normal flow:** User clicks `Back` - Button. Archive View is closed.  
**Alternative flow:** 
User logs out / is logged out via timeout. Archive View is closed and will not be displayed at the next login.  
**Error flow:** -
