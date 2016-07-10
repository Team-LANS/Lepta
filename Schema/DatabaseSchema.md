#Database Schema

User (**Name**, Password, Color)  
Bill (**Timestamp**, *User.Name*, Total)  
Item (**Description**, *Bill.Timestamp*, *Bill.Name*, Price)  
Assigned_Item (**Description**, *Bill.Timestamp*, *Bill.Name*, Archived)  
owns (**User.Name**, **Assigned_Item.Description**, **Assigned_Item.Timestamp**, **Assigned_Item.Name**)