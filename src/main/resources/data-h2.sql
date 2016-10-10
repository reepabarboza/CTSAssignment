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


insert into Plan_Coverage(Main_Category,Sub_Category,plan_id,deductible_percentage,deductible_Amt,deductible_rule) 
values('Preventive Care','ROUTINE PHYSICAL EXAM','P001',0,0,'No Charge');
insert into Plan_Coverage(Main_Category,Sub_Category,plan_id,deductible_percentage,deductible_Amt,deductible_rule) 
values('Preventive Care','ROUTINE PHYSICAL EXAM','P002',0,0,'No Charge');
insert into Plan_Coverage(Main_Category,Sub_Category,plan_id,deductible_percentage,deductible_Amt,deductible_rule) 
values('Preventive Care','ROUTINE PHYSICAL EXAM','P003',0,0,'No Charge');

insert into Plan_Coverage(Main_Category,Sub_Category,plan_id,deductible_percentage,deductible_Amt,deductible_rule) 
values('Preventive Care','MAMMOGRAMS','P001',0,0,'No Charge');
insert into Plan_Coverage(Main_Category,Sub_Category,plan_id,deductible_percentage,deductible_Amt,deductible_rule) 
values('Preventive Care','MAMMOGRAMS','P002',0,0,'No Charge');
insert into Plan_Coverage(Main_Category,Sub_Category,plan_id,deductible_percentage,deductible_Amt,deductible_rule) 
values('Preventive Care','MAMMOGRAMS','P003',0,0,'No Charge');

insert into Plan_Coverage(Main_Category,Sub_Category,plan_id,deductible_percentage,deductible_Amt,deductible_rule) 
values('Outpatient Services','PRIMARY CARE OFFICE VISIT','P001',40,0,'40% AFTER DEDUCTIBLE');
insert into Plan_Coverage(Main_Category,Sub_Category,plan_id,deductible_percentage,deductible_Amt,deductible_rule) 
values('Outpatient Services','PRIMARY CARE OFFICE VISIT','P002',50,0,'50% AFTER DEDUCTIBLE');
insert into Plan_Coverage(Main_Category,Sub_Category,plan_id,deductible_percentage,deductible_Amt,deductible_rule) 
values('Outpatient Services','PRIMARY CARE OFFICE VISIT','P003',60,0,'60% AFTER DEDUCTIBLE');

insert into Plan_Coverage(Main_Category,Sub_Category,plan_id,deductible_percentage,deductible_Amt,deductible_rule) 
values('Outpatient Services','SPECIALTY CARE OFFICE VISIT','P001',70,0,'70% AFTER DEDUCTIBLE');
insert into Plan_Coverage(Main_Category,Sub_Category,plan_id,deductible_percentage,deductible_Amt,deductible_rule) 
values('Outpatient Services','SPECIALTY CARE OFFICE VISIT','P002',80,0,'80% AFTER DEDUCTIBLE');
insert into Plan_Coverage(Main_Category,Sub_Category,plan_id,deductible_percentage,deductible_Amt,deductible_rule) 
values('Outpatient Services','SPECIALTY CARE OFFICE VISIT','P003',90,0,'90% AFTER DEDUCTIBLE');

insert into Plan_Coverage(Main_Category,Sub_Category,plan_id,deductible_percentage,deductible_Amt,deductible_rule) 
values('Outpatient Services','X-RAYS','P001',40,0,'40% AFTER DEDUCTIBLE');
insert into Plan_Coverage(Main_Category,Sub_Category,plan_id,deductible_percentage,deductible_Amt,deductible_rule) 
values('Outpatient Services','X-RAYS','P002',50,0,'50% AFTER DEDUCTIBLE');
insert into Plan_Coverage(Main_Category,Sub_Category,plan_id,deductible_percentage,deductible_Amt,deductible_rule) 
values('Outpatient Services','X-RAYS','P003',60,0,'60% AFTER DEDUCTIBLE');

insert into Plan_Coverage(Main_Category,Sub_Category,plan_id,deductible_percentage,deductible_Amt,deductible_rule) 
values('Outpatient Services','LAB TESTS','P001',80,0,'80% AFTER DEDUCTIBLE');
insert into Plan_Coverage(Main_Category,Sub_Category,plan_id,deductible_percentage,deductible_Amt,deductible_rule) 
values('Outpatient Services','LAB TESTS','P002',90,0,'90% AFTER DEDUCTIBLE');
insert into Plan_Coverage(Main_Category,Sub_Category,plan_id,deductible_percentage,deductible_Amt,deductible_rule) 
values('Outpatient Services','LAB TESTS','P003',100,0,'100% AFTER DEDUCTIBLE');

insert into Plan_Coverage(Main_Category,Sub_Category,plan_id,deductible_percentage,deductible_Amt,deductible_rule) 
values('Outpatient Services','MRI','P001',40,0,'40% AFTER DEDUCTIBLE');
insert into Plan_Coverage(Main_Category,Sub_Category,plan_id,deductible_percentage,deductible_Amt,deductible_rule) 
values('Outpatient Services','MRI','P002',50,0,'50% AFTER DEDUCTIBLE');
insert into Plan_Coverage(Main_Category,Sub_Category,plan_id,deductible_percentage,deductible_Amt,deductible_rule) 
values('Outpatient Services','MRI','P003',60,0,'60% AFTER DEDUCTIBLE');

insert into Plan_Coverage(Main_Category,Sub_Category,plan_id,deductible_percentage,deductible_Amt,deductible_rule) 
values('Outpatient Services','CT','P001',40,0,'40% AFTER DEDUCTIBLE');
insert into Plan_Coverage(Main_Category,Sub_Category,plan_id,deductible_percentage,deductible_Amt,deductible_rule) 
values('Outpatient Services','CT','P002',50,0,'50% AFTER DEDUCTIBLE');
insert into Plan_Coverage(Main_Category,Sub_Category,plan_id,deductible_percentage,deductible_Amt,deductible_rule) 
values('Outpatient Services','CT','P003',60,0,'60% AFTER DEDUCTIBLE');

insert into Plan_Coverage(Main_Category,Sub_Category,plan_id,deductible_percentage,deductible_Amt,deductible_rule) 
values('Outpatient Services','PET','P001',40,0,'40% AFTER DEDUCTIBLE');
insert into Plan_Coverage(Main_Category,Sub_Category,plan_id,deductible_percentage,deductible_Amt,deductible_rule) 
values('Outpatient Services','PET','P002',50,0,'50% AFTER DEDUCTIBLE');
insert into Plan_Coverage(Main_Category,Sub_Category,plan_id,deductible_percentage,deductible_Amt,deductible_rule) 
values('Outpatient Services','PET','P003',60,0,'60% AFTER DEDUCTIBLE');

insert into Plan_Coverage(Main_Category,Sub_Category,plan_id,deductible_percentage,deductible_Amt,deductible_rule) 
values('Outpatient Services','OUTPATIENT SURGERY','P001',40,0,'40% AFTER DEDUCTIBLE');
insert into Plan_Coverage(Main_Category,Sub_Category,plan_id,deductible_percentage,deductible_Amt,deductible_rule) 
values('Outpatient Services','OUTPATIENT SURGERY','P002',50,0,'50% AFTER DEDUCTIBLE');
insert into Plan_Coverage(Main_Category,Sub_Category,plan_id,deductible_percentage,deductible_Amt,deductible_rule) 
values('Outpatient Services','OUTPATIENT SURGERY','P003',60,0,'60% AFTER DEDUCTIBLE');

insert into Plan_Coverage(Main_Category,Sub_Category,plan_id,deductible_percentage,deductible_Amt,deductible_rule) 
values('Outpatient Services','MENTAL HEALTH VISIT','P001',60,0,'60% AFTER DEDUCTIBLE');
insert into Plan_Coverage(Main_Category,Sub_Category,plan_id,deductible_percentage,deductible_Amt,deductible_rule) 
values('Outpatient Services','MENTAL HEALTH VISIT','P002',70,0,'70% AFTER DEDUCTIBLE');
insert into Plan_Coverage(Main_Category,Sub_Category,plan_id,deductible_percentage,deductible_Amt,deductible_rule) 
values('Outpatient Services','MENTAL HEALTH VISIT','P003',80,0,'80% AFTER DEDUCTIBLE');


insert into Plan_Coverage(Main_Category,Sub_Category,plan_id,deductible_percentage,deductible_Amt,deductible_rule) 
values('Inpatient Hospital Care','ROOM AND BOARD','P001',40,0,'40% AFTER DEDUCTIBLE');
insert into Plan_Coverage(Main_Category,Sub_Category,plan_id,deductible_percentage,deductible_Amt,deductible_rule) 
values('Inpatient Hospital Care','ROOM AND BOARD','P002',50,0,'50% AFTER DEDUCTIBLE');
insert into Plan_Coverage(Main_Category,Sub_Category,plan_id,deductible_percentage,deductible_Amt,deductible_rule) 
values('Inpatient Hospital Care','ROOM AND BOARD','P003',60,0,'60% AFTER DEDUCTIBLE');

insert into Plan_Coverage(Main_Category,Sub_Category,plan_id,deductible_percentage,deductible_Amt,deductible_rule) 
values('Inpatient Hospital Care','SURGERY','P001',40,0,'40% AFTER DEDUCTIBLE');
insert into Plan_Coverage(Main_Category,Sub_Category,plan_id,deductible_percentage,deductible_Amt,deductible_rule) 
values('Inpatient Hospital Care','SURGERY','P002',50,0,'50% AFTER DEDUCTIBLE');
insert into Plan_Coverage(Main_Category,Sub_Category,plan_id,deductible_percentage,deductible_Amt,deductible_rule) 
values('Inpatient Hospital Care','SURGERY','P003',60,0,'60% AFTER DEDUCTIBLE');

insert into Plan_Coverage(Main_Category,Sub_Category,plan_id,deductible_percentage,deductible_Amt,deductible_rule) 
values('Inpatient Hospital Care','ANESTHESIA','P001',40,0,'40% AFTER DEDUCTIBLE');
insert into Plan_Coverage(Main_Category,Sub_Category,plan_id,deductible_percentage,deductible_Amt,deductible_rule) 
values('Inpatient Hospital Care','ANESTHESIA','P002',50,0,'50% AFTER DEDUCTIBLE');
insert into Plan_Coverage(Main_Category,Sub_Category,plan_id,deductible_percentage,deductible_Amt,deductible_rule) 
values('Inpatient Hospital Care','ANESTHESIA','P003',60,0,'60% AFTER DEDUCTIBLE');

insert into Plan_Coverage(Main_Category,Sub_Category,plan_id,deductible_percentage,deductible_Amt,deductible_rule) 
values('Inpatient Hospital Care','X-RAYS','P001',40,0,'40% AFTER DEDUCTIBLE');
insert into Plan_Coverage(Main_Category,Sub_Category,plan_id,deductible_percentage,deductible_Amt,deductible_rule) 
values('Inpatient Hospital Care','X-RAYS','P002',50,0,'50% AFTER DEDUCTIBLE');
insert into Plan_Coverage(Main_Category,Sub_Category,plan_id,deductible_percentage,deductible_Amt,deductible_rule) 
values('Inpatient Hospital Care','X-RAYS','P003',60,0,'60% AFTER DEDUCTIBLE');

insert into Plan_Coverage(Main_Category,Sub_Category,plan_id,deductible_percentage,deductible_Amt,deductible_rule) 
values('Inpatient Hospital Care','LAB TESTS','P001',40,0,'40% AFTER DEDUCTIBLE');
insert into Plan_Coverage(Main_Category,Sub_Category,plan_id,deductible_percentage,deductible_Amt,deductible_rule) 
values('Inpatient Hospital Care','LAB TESTS','P002',50,0,'50% AFTER DEDUCTIBLE');
insert into Plan_Coverage(Main_Category,Sub_Category,plan_id,deductible_percentage,deductible_Amt,deductible_rule) 
values('Inpatient Hospital Care','LAB TESTS','P003',60,0,'60% AFTER DEDUCTIBLE');

insert into Plan_Coverage(Main_Category,Sub_Category,plan_id,deductible_percentage,deductible_Amt,deductible_rule) 
values('Inpatient Hospital Care','MEDICATIONS','P001',40,0,'40% AFTER DEDUCTIBLE');
insert into Plan_Coverage(Main_Category,Sub_Category,plan_id,deductible_percentage,deductible_Amt,deductible_rule) 
values('Inpatient Hospital Care','MEDICATIONS','P002',50,0,'50% AFTER DEDUCTIBLE');
insert into Plan_Coverage(Main_Category,Sub_Category,plan_id,deductible_percentage,deductible_Amt,deductible_rule) 
values('Inpatient Hospital Care','MEDICATIONS','P003',60,0,'60% AFTER DEDUCTIBLE');

insert into Plan_Coverage(Main_Category,Sub_Category,plan_id,deductible_percentage,deductible_Amt,deductible_rule) 
values('Inpatient Hospital Care','MENTAL HEALTH CARE','P001',40,0,'40% AFTER DEDUCTIBLE');
insert into Plan_Coverage(Main_Category,Sub_Category,plan_id,deductible_percentage,deductible_Amt,deductible_rule) 
values('Inpatient Hospital Care','MENTAL HEALTH CARE','P002',50,0,'50% AFTER DEDUCTIBLE');
insert into Plan_Coverage(Main_Category,Sub_Category,plan_id,deductible_percentage,deductible_Amt,deductible_rule) 
values('Inpatient Hospital Care','MENTAL HEALTH CARE','P003',60,0,'60% AFTER DEDUCTIBLE');

insert into Plan_Coverage(Main_Category,Sub_Category,plan_id,deductible_percentage,deductible_Amt,deductible_rule) 
values('Maternity','ROUTINE PRENATAL CARE VISIT','P001',40,0,'40% AFTER DEDUCTIBLE');
insert into Plan_Coverage(Main_Category,Sub_Category,plan_id,deductible_percentage,deductible_Amt,deductible_rule) 
values('Maternity','ROUTINE PRENATAL CARE VISIT','P002',40,0,'40% AFTER DEDUCTIBLE');
insert into Plan_Coverage(Main_Category,Sub_Category,plan_id,deductible_percentage,deductible_Amt,deductible_rule) 
values('Maternity','ROUTINE PRENATAL CARE VISIT','P003',30,0,'30% AFTER DEDUCTIBLE');

insert into Plan_Coverage(Main_Category,Sub_Category,plan_id,deductible_percentage,deductible_Amt,deductible_rule) 
values('Maternity','FIRST POSTPARTUM VISIT','P001',40,0,'40% AFTER DEDUCTIBLE');
insert into Plan_Coverage(Main_Category,Sub_Category,plan_id,deductible_percentage,deductible_Amt,deductible_rule) 
values('Maternity','FIRST POSTPARTUM VISIT','P002',40,0,'40% AFTER DEDUCTIBLE');
insert into Plan_Coverage(Main_Category,Sub_Category,plan_id,deductible_percentage,deductible_Amt,deductible_rule) 
values('Maternity','FIRST POSTPARTUM VISIT','P003',30,0,'30% AFTER DEDUCTIBLE');

insert into Plan_Coverage(Main_Category,Sub_Category,plan_id,deductible_percentage,deductible_Amt,deductible_rule) 
values('Maternity','DELIVERY AND INPATIENT WELL-BABY CARE','P001',40,0,'40% AFTER DEDUCTIBLE');
insert into Plan_Coverage(Main_Category,Sub_Category,plan_id,deductible_percentage,deductible_Amt,deductible_rule) 
values('Maternity','DELIVERY AND INPATIENT WELL-BABY CARE','P002',40,0,'40% AFTER DEDUCTIBLE');
insert into Plan_Coverage(Main_Category,Sub_Category,plan_id,deductible_percentage,deductible_Amt,deductible_rule) 
values('Maternity','DELIVERY AND INPATIENT WELL-BABY CARE','P003',30,0,'30% AFTER DEDUCTIBLE');

insert into Plan_Coverage(Main_Category,Sub_Category,plan_id,deductible_percentage,deductible_Amt,deductible_rule) 
values('Emergency And Urgent Care','EMERGENCY DEPARTMENT VISIT','P001',40,0,'40% AFTER DEDUCTIBLE');
insert into Plan_Coverage(Main_Category,Sub_Category,plan_id,deductible_percentage,deductible_Amt,deductible_rule) 
values('Emergency And Urgent Care','EMERGENCY DEPARTMENT VISIT','P002',50,0,'50% AFTER DEDUCTIBLE');
insert into Plan_Coverage(Main_Category,Sub_Category,plan_id,deductible_percentage,deductible_Amt,deductible_rule) 
values('Emergency And Urgent Care','EMERGENCY DEPARTMENT VISIT','P003',60,0,'60% AFTER DEDUCTIBLE');

insert into Plan_Coverage(Main_Category,Sub_Category,plan_id,deductible_percentage,deductible_Amt,deductible_rule) 
values('Emergency And Urgent Care','URGENT CARE VISIT','P001',40,0,'40% AFTER DEDUCTIBLE');
insert into Plan_Coverage(Main_Category,Sub_Category,plan_id,deductible_percentage,deductible_Amt,deductible_rule) 
values('Emergency And Urgent Care','URGENT CARE VISIT','P002',0,100,'50% AFTER DEDUCTIBLE');
insert into Plan_Coverage(Main_Category,Sub_Category,plan_id,deductible_percentage,deductible_Amt,deductible_rule) 
values('Emergency And Urgent Care','URGENT CARE VISIT','P003',0,120,'60% AFTER DEDUCTIBLE');

insert into Plan_Coverage(Main_Category,Sub_Category,plan_id,deductible_percentage,deductible_Amt,deductible_rule) 
values('Emergency And Urgent Care','AMBULANCE SERVICES','P001',40,0,'40% AFTER DEDUCTIBLE');
insert into Plan_Coverage(Main_Category,Sub_Category,plan_id,deductible_percentage,deductible_Amt,deductible_rule) 
values('Emergency And Urgent Care','AMBULANCE SERVICES','P002',40,0,'40% AFTER DEDUCTIBLE');
insert into Plan_Coverage(Main_Category,Sub_Category,plan_id,deductible_percentage,deductible_Amt,deductible_rule) 
values('Emergency And Urgent Care','AMBULANCE SERVICES','P003',30,0,'30% AFTER DEDUCTIBLE');


insert into Plan_Coverage(Main_Category,Sub_Category,plan_id,deductible_percentage,deductible_Amt,deductible_rule) 
values('Prescription Drugs','GENERIC','P001',60,0,'60% AFTER DEDUCTIBLE');
insert into Plan_Coverage(Main_Category,Sub_Category,plan_id,deductible_percentage,deductible_Amt,deductible_rule) 
values('Prescription Drugs','GENERIC','P002',70,0,'70% AFTER DEDUCTIBLE');
insert into Plan_Coverage(Main_Category,Sub_Category,plan_id,deductible_percentage,deductible_Amt,deductible_rule) 
values('Prescription Drugs','GENERIC','P003',80,0,'80% AFTER DEDUCTIBLE');

insert into Plan_Coverage(Main_Category,Sub_Category,plan_id,deductible_percentage,deductible_Amt,deductible_rule) 
values('Prescription Drugs','PREFERRED BRAND','P001',50,0,'50% AFTER DEDUCTIBLE');
insert into Plan_Coverage(Main_Category,Sub_Category,plan_id,deductible_percentage,deductible_Amt,deductible_rule) 
values('Prescription Drugs','PREFERRED BRAND','P002',60,0,'60% AFTER DEDUCTIBLE');
insert into Plan_Coverage(Main_Category,Sub_Category,plan_id,deductible_percentage,deductible_Amt,deductible_rule) 
values('Prescription Drugs','PREFERRED BRAND','P003',70,0,'70% AFTER DEDUCTIBLE');

insert into Plan_Coverage(Main_Category,Sub_Category,plan_id,deductible_percentage,deductible_Amt,deductible_rule) 
values('Prescription Drugs','NON-PREFERRED BRAND','P001',40,0,'40% AFTER DEDUCTIBLE');
insert into Plan_Coverage(Main_Category,Sub_Category,plan_id,deductible_percentage,deductible_Amt,deductible_rule) 
values('Prescription Drugs','NON-PREFERRED BRAND','P002',50,0,'50% AFTER DEDUCTIBLE');
insert into Plan_Coverage(Main_Category,Sub_Category,plan_id,deductible_percentage,deductible_Amt,deductible_rule) 
values('Prescription Drugs','NON-PREFERRED BRAND','P003',60,0,'60% AFTER DEDUCTIBLE');

insert into Plan_Coverage(Main_Category,Sub_Category,plan_id,deductible_percentage,deductible_Amt,deductible_rule) 
values('Prescription Drugs','SPECIALTY','P001',40,0,'40% AFTER DEDUCTIBLE');
insert into Plan_Coverage(Main_Category,Sub_Category,plan_id,deductible_percentage,deductible_Amt,deductible_rule) 
values('Prescription Drugs','SPECIALTY','P002',50,0,'50% AFTER DEDUCTIBLE');
insert into Plan_Coverage(Main_Category,Sub_Category,plan_id,deductible_percentage,deductible_Amt,deductible_rule) 
values('Prescription Drugs','SPECIALTY','P003'60,80,0,'60% AFTER DEDUCTIBLE');


