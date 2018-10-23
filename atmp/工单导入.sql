-- BusinessType表新增二级菜单
insert into BusinessType(BTKey, BTTypeCode, BTTypeName, BTFuncCode, BTPARTITIONCode)
values ('115400', '021204', '批量建单', NULL, '1');

-- ObjectDetail表新增数据
delete from ObjectDetail where BTKey = '115400';
/*
insert into ObjectDetail(BTKey, pic1, pic2, pic3, pic4, name, URL)
values ('115400', 'images/left/115400-1.gif', 'images/left/115400-2.gif', 'images/left/115400-3.gif', 'images/left/115400-4.gif', '批量建单', 'WorkOrderImp.jsp');
*/
-- 临时
insert into ObjectDetail(BTKey, pic1, pic2, pic3, pic4, name, URL)
values ('115400', 'images/left/200-1.gif', 'images/left/200-2.gif', 'images/left/200-3.gif', 'images/left/200-4.gif', '批量建单', 'WorkOrderImpServlet');

-- ObjectRaletion表新增数据
delete from ObjectRaletion where BTKey = '115400';
insert into ObjectRaletion(BTKey, rBTKey, GROUPNUM, Sort) values ('115400', '101200', '3', '1');
insert into ObjectRaletion(BTKey, rBTKey, GROUPNUM, Sort) values ('115400', '108100', '2', '4');
insert into ObjectRaletion(BTKey, rBTKey, GROUPNUM, Sort) values ('115400', '111100', '1', '0');
insert into ObjectRaletion(BTKey, rBTKey, GROUPNUM, Sort) values ('115400', '115100', '1', '1');
insert into ObjectRaletion(BTKey, rBTKey, GROUPNUM, Sort) values ('115400', '115160', '2', '1');
insert into ObjectRaletion(BTKey, rBTKey, GROUPNUM, Sort) values ('115400', '115200', '1', '2');
insert into ObjectRaletion(BTKey, rBTKey, GROUPNUM, Sort) values ('115400', '115300', '1', '3');

delete from ObjectRaletion where rBTKey = '115400';
insert into ObjectRaletion(BTKey, rBTKey, GROUPNUM, Sort) values ('115400', '115400', '3', '8');
insert into ObjectRaletion(BTKey, rBTKey, GROUPNUM, Sort) values ('108100', '115400', '3', '8');
insert into ObjectRaletion(BTKey, rBTKey, GROUPNUM, Sort) values ('111100', '115400', '3', '8');
insert into ObjectRaletion(BTKey, rBTKey, GROUPNUM, Sort) values ('115100', '115400', '3', '8');
insert into ObjectRaletion(BTKey, rBTKey, GROUPNUM, Sort) values ('115160', '115400', '3', '8');
insert into ObjectRaletion(BTKey, rBTKey, GROUPNUM, Sort) values ('115200', '115400', '3', '8');
insert into ObjectRaletion(BTKey, rBTKey, GROUPNUM, Sort) values ('115300', '115400', '3', '8');

