#Database Schema

User (**Name**, Color, Password)  
Bill (**Nr**, Status, Timestamp, User.Name)  
Item (**Id**, *Bill.Nr*, Description, Price)  
owns (**User.Name**, **Item.Nr**, **Item.Id**)