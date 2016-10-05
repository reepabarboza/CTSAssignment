insert into Plan_Description(Plan_Id,Plan_Name,Coverage_Type,Estimated_Premium,Annual_Deductible_Individual,Annual_Deductible_Family) values('P001','Bronze 6000/40%/HAS','Family', 962.00,6000,12000);
insert into Plan_Description(Plan_Id,Plan_Name,Coverage_Type,Estimated_Premium,Annual_Deductible_Individual,Annual_Deductible_Family) values('P002','Bronze 5000/50','Family',1017.00,5000,10000);
insert into Plan_Description(Plan_Id,Plan_Name,Coverage_Type,Estimated_Premium,Annual_Deductible_Individual,Annual_Deductible_Family) values('P003','Bronze 4000/20','Family',1045.67,4000,8000);

insert into Policy_Data(Policy_Id,Policy_Holder_Id,First_Name,Last_Name,Plan_Id,Coverage_Start_Date,Individual_Accumulated_Ded,Family_Accumulated_Ded) 
values('100001','1000011','Sam','Collins','P001',TIMESTAMP '2004-01-01 00:00:00',0.00,0);
insert into Policy_Data(Policy_Id,Policy_Holder_Id,First_Name,Last_Name,Plan_Id,Coverage_Start_Date,Individual_Accumulated_Ded,Family_Accumulated_Ded) 
values('100001','1000012','Jina','Collins','P001',TIMESTAMP '2008-12-05 00:00:00',0.00,0);
insert into Policy_Data(Policy_Id,Policy_Holder_Id,First_Name,Last_Name,Plan_Id,Coverage_Start_Date,Individual_Accumulated_Ded,Family_Accumulated_Ded) 
values('100001','1000013','Nancy','Collins','P001',TIMESTAMP '2010-06-05 00:00:00',0.00,0);
insert into Policy_Data(Policy_Id,Policy_Holder_Id,First_Name,Last_Name,Plan_Id,Coverage_Start_Date,Individual_Accumulated_Ded,Family_Accumulated_Ded) 
values('100001','1000014','Jack','Collins','P001',TIMESTAMP '2013-08-07 00:00:00',0.00,0);


insert into Plan_Coverage(Main_Category,Sub_Category,plan_id,deductible_percentage,deductible_rule) 
values('Preventive Care','ROUTINE PHYSICAL EXAM','P001',0,'No Charge');
insert into Plan_Coverage(Main_Category,Sub_Category,plan_id,deductible_percentage,deductible_rule) 
values('Preventive Care','ROUTINE PHYSICAL EXAM','P002',0,'No Charge');
insert into Plan_Coverage(Main_Category,Sub_Category,plan_id,deductible_percentage,deductible_rule) 
values('Preventive Care','ROUTINE PHYSICAL EXAM','P003',0,'No Charge');

insert into Plan_Coverage(Main_Category,Sub_Category,plan_id,deductible_percentage,deductible_rule) 
values('Preventive Care','MAMMOGRAMS','P001',0,'No Charge');
insert into Plan_Coverage(Main_Category,Sub_Category,plan_id,deductible_percentage,deductible_rule) 
values('Preventive Care','MAMMOGRAMS','P002',0,'No Charge');
insert into Plan_Coverage(Main_Category,Sub_Category,plan_id,deductible_percentage,deductible_rule) 
values('Preventive Care','MAMMOGRAMS','P003',0,'No Charge');

insert into Plan_Coverage(Main_Category,Sub_Category,plan_id,deductible_percentage,deductible_rule) 
values('Outpatient Services','PRIMARY CARE OFFICE VISIT','P001',40,'40% AFTER DEDUCTIBLE');
insert into Plan_Coverage(Main_Category,Sub_Category,plan_id,deductible_percentage,deductible_rule) 
values('Outpatient Services','PRIMARY CARE OFFICE VISIT','P002',50,'50% AFTER DEDUCTIBLE');
insert into Plan_Coverage(Main_Category,Sub_Category,plan_id,deductible_percentage,deductible_rule) 
values('Outpatient Services','PRIMARY CARE OFFICE VISIT','P003',60,'60% AFTER DEDUCTIBLE');

insert into Plan_Coverage(Main_Category,Sub_Category,plan_id,deductible_percentage,deductible_rule) 
values('Outpatient Services','SPECIALTY CARE OFFICE VISIT','P001',70,'70% AFTER DEDUCTIBLE');
insert into Plan_Coverage(Main_Category,Sub_Category,plan_id,deductible_percentage,deductible_rule) 
values('Outpatient Services','SPECIALTY CARE OFFICE VISIT','P002',80,'80% AFTER DEDUCTIBLE');
insert into Plan_Coverage(Main_Category,Sub_Category,plan_id,deductible_percentage,deductible_rule) 
values('Outpatient Services','SPECIALTY CARE OFFICE VISIT','P003',90,'90% AFTER DEDUCTIBLE');

insert into Plan_Coverage(Main_Category,Sub_Category,plan_id,deductible_percentage,deductible_rule) 
values('Outpatient Services','OUTPATIENT SURGERY','P001',40,'40% AFTER DEDUCTIBLE');
insert into Plan_Coverage(Main_Category,Sub_Category,plan_id,deductible_percentage,deductible_rule) 
values('Outpatient Services','OUTPATIENT SURGERY','P002',50,'50% AFTER DEDUCTIBLE');
insert into Plan_Coverage(Main_Category,Sub_Category,plan_id,deductible_percentage,deductible_rule) 
values('Outpatient Services','OUTPATIENT SURGERY','P003',60,'60% AFTER DEDUCTIBLE');


insert into Plan_Coverage(Main_Category,Sub_Category,plan_id,deductible_percentage,deductible_rule) 
values('Inpatient Hospital Care','ROOM AND BOARD','P001',40,'40% AFTER DEDUCTIBLE');
insert into Plan_Coverage(Main_Category,Sub_Category,plan_id,deductible_percentage,deductible_rule) 
values('Inpatient Hospital Care','ROOM AND BOARD','P002',50,'50% AFTER DEDUCTIBLE');
insert into Plan_Coverage(Main_Category,Sub_Category,plan_id,deductible_percentage,deductible_rule) 
values('Inpatient Hospital Care','ROOM AND BOARD','P003',60,'60% AFTER DEDUCTIBLE');

insert into Plan_Coverage(Main_Category,Sub_Category,plan_id,deductible_percentage,deductible_rule) 
values('Inpatient Hospital Care','SURGERY','P001',40,'40% AFTER DEDUCTIBLE');
insert into Plan_Coverage(Main_Category,Sub_Category,plan_id,deductible_percentage,deductible_rule) 
values('Inpatient Hospital Care','SURGERY','P002',50,'50% AFTER DEDUCTIBLE');
insert into Plan_Coverage(Main_Category,Sub_Category,plan_id,deductible_percentage,deductible_rule) 
values('Inpatient Hospital Care','SURGERY','P003',60,'60% AFTER DEDUCTIBLE');


insert into Plan_Coverage(Main_Category,Sub_Category,plan_id,deductible_percentage,deductible_rule) 
values('Prescription Drugs','GENERIC','P001',60,'60% AFTER DEDUCTIBLE');
insert into Plan_Coverage(Main_Category,Sub_Category,plan_id,deductible_percentage,deductible_rule) 
values('Prescription Drugs','GENERIC','P002',70,'70% AFTER DEDUCTIBLE');
insert into Plan_Coverage(Main_Category,Sub_Category,plan_id,deductible_percentage,deductible_rule) 
values('Prescription Drugs','GENERIC','P003',80,'80% AFTER DEDUCTIBLE');



