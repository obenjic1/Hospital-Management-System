
INSERT INTO `role` (`id`, `created_at`, `description`, `name`) VALUES
(1, NULL, 'Listing users in the system refers to obtaining a list of all user accounts that have been created on a particular operating system', 'ROLE_LIST_USERS'),
(2, NULL, 'Viewing user details in the system means accessing and examining the specific information associated with a user account', 'ROLE_VIEW_USER_DETAILS'),
(3, NULL, 'Updating users in the system refers to modifying or changing the attributes and settings associated with existing user accounts', 'ROLE_UPDATE_USER'),
(4, NULL, 'Removing users from the system refers to the process of deleting or disabling user accounts that are no longer needed or required', 'ROLE_REMOVE_USER'),
(5, NULL, 'Adding a group to the system means creating a new group account that can be used to the operating system.', 'ROLE_ADD_GROUP'),
(6, NULL, 'Listing group in the system refers to obtaining a list of all groups that have been created on a particular operating system', 'ROLE_LIST_GROUPS'),
(7, NULL, 'Viewing group details in the system means accessing and examining the specific information associated with a group', 'ROLE_VIEW_GROUP_DETAILS'),
(8, NULL, 'Updating group in the system refers to modifying or changing the attributes and settings associated with existing group', 'ROLE_UPDATE_GROUP'),
(9, NULL, 'block group from the system refers to the process of denied or disabling all right access to this group', 'ROLE_DENIED_GROUP'),
(10, NULL, 'Listing roles in the system refers to obtaining a list of all roles that have been created on our system', 'ROLE_LIST_ROLES'),
(11, NULL, 'Viewing role details in the system means accessing and examining the specific information associated with a group', 'ROLE_VIEW_ROLE_DETAILS'),
(12, NULL, 'Adding a customer to the system means creating a new customer account that can be used to the operating system', 'ROLE_SAVE_CUSTOMER'),
(13, NULL, 'Listing customer in the system refers to obtaining a list of all customer that have been created on our system', 'ROLE_LIST_CUSTOMER'),
(14, NULL, 'Viewing customer details in the system means accessing and examining the specific information associated with a customer', 'ROLE_VIEW_CUSTOMER_DETAILS'),
(15, NULL, 'Remove customer means that you''ll delete all the customer informations and producs relate with him', 'ROLE_DELETE_CUSTOMER'),
(16, NULL, 'Updating customer in the system refers to modifying or changing the attributes and settings associated with existing customer', 'ROLE_UPDATE_CUSTOMER'),
(17, NULL, 'Manage setings means that you have the rigth access to add, view, delete and update everything have rapport with setings', 'ROLE_MANAGE_SETINGS'),
(18, NULL, 'View setting means that you can only view and select the settings element you cannot do the others operations.', 'ROLE_VIEW_SETINGS'),
(19, NULL, 'Default role to manage users', 'ROLE_ADD_USER'),
(20, NULL, 'Default role to manage jobs', 'ROLE_REGISTER_NEW_JOB'),
(21, NULL, 'Default role to manage draft jobs', 'ROLE_EDIT_DRAFT_JOB'),
(22, NULL, 'Default role to manage switch jobs to different departement', 'ROLE_MOVE_JOB_TO_DEPARTEMENT'),
(23, NULL, 'Default role to accept or abort jobs', 'ROLE_ABORT_DRAFT_OR_JOB'),
(24, NULL, 'Default role to confirm jobs', 'ROLE_CONFIRM_JOB'),
(25, NULL, 'Default role to approve jobs by the marketing manager', 'ROLE_APPROVE_JOB'),
(26, NULL, 'Default role to add forecast delivery date to the jobs by the marketing manager', 'ROLE_SET_FORECAST_DELIVERY_DATE'),
(27, NULL, 'Default role to apply discount to the estimate by the marketing manager', 'ROLE_APPLY_DISCOUNT'),
(28, NULL, 'Default role to generate an invoice by the marketing manager', 'ROLE_GENERATE_INVOICE'),
(29, NULL, NULL, 'ROLE_APPLY_COMMISSION'),
(30, NULL, NULL, 'ROLE_APPLY_COMMISION_AS_DISCOUNT'),
(31, NULL, NULL, 'ROLE_APPLY_TAX');

INSERT INTO `groupe` (`id`, `created_at`, `description`, `enabled`, `name`) VALUES
(1, '2024-08-19 14:00:21.512381', 'Only admin can have all the rigth access\nOnly admin can have all the rigth access\nOnly admin can have all the rigth access\nOnly admin can have all the rigth accessOnly admin can have all the rigth access', b'1', 'ADMIN');

INSERT INTO `groupe_role` (`groupe_id`, `role_id`) VALUES
(1, 1),
(1, 2),
(1, 3),
(1, 4),
(1, 5),
(1, 6),
(1, 7),
(1, 8),
(1, 9),
(1, 10),
(1, 11),
(1, 12),
(1, 13),
(1, 14),
(1, 15),
(1, 16),
(1, 17),
(1, 18),
(1, 19),
(1, 20),
(1, 21),
(1, 22),
(1, 23),
(1, 24),
(1, 25),
(1, 26),
(1, 27),
(1, 28),
(1, 29),
(1, 30),
(1, 31);


INSERT INTO `user` (`address`,`confirm_password`, `created_at`, `active`, `email`, `first_name`, `image_path`, `last_name`, `mobile`, `password`, `username`, `groupe_id`, `reset_password_token`, `connected`) VALUES 
('ville','$2a$10$87EX5oVUFrl0QKlvOLp.6.slgA2oymG/I4UHld28l2.Bk6fhuYILm', '2024-03-19', b'0', 'vincentjoel@gmail.com', 'DJAMA', NULL, '', '691-67-86-65', '$2a$10$87EX5oVUFrl0QKlvOLp.6.slgA2oymG/I4UHld28l2.Bk6fhuYILm', 'vincent', 1, NULL, b'0');

INSERT INTO `binding_type`(`name`) VALUES ("Glue-Bound");
INSERT INTO `binding_type`(`name`) VALUES ("Glue-Left");
INSERT INTO `binding_type`(`name`) VALUES ("Glue-Head");
INSERT INTO `binding_type`(`name`) VALUES ("Perfect Binding");
INSERT INTO `binding_type`(`name`) VALUES ("Saddle Stitching");
INSERT INTO `binding_type`(`name`) VALUES ("Spiral Binding");
INSERT INTO `binding_type`(`name`) VALUES ("Wire-O Binding");
INSERT INTO `binding_type`(`name`) VALUES ("Case Binding");
INSERT INTO `binding_type`(`name`) VALUES ("Layflat Binding");
INSERT INTO `binding_type`(`name`) VALUES ("Hand Gathering");
INSERT INTO `binding_type`(`name`) VALUES ("Gather Sticher");
INSERT INTO `binding_type`(`name`) VALUES ("Stitching");

INSERT INTO `content_type`(`name`) VALUES ("Cover");
INSERT INTO `content_type`(`name`) VALUES ("Content");

INSERT INTO `department`(`name`) VALUES ("Marketing");
INSERT INTO `department`(`name`) VALUES ("Typesetting");
INSERT INTO `department`(`name`) VALUES ("Mounting");
INSERT INTO `department`(`name`) VALUES ("Printing");
INSERT INTO `department`(`name`) VALUES ("Finishing");

INSERT INTO `job_type`(`name`, `category`) VALUES ("Catalogs", 1);
INSERT INTO `job_type`(`name`, `category`) VALUES ("Magazines", 2);
INSERT INTO `job_type`(`name`, `category`) VALUES ("Book", 2);
INSERT INTO `job_type`(`name`, `category`) VALUES ("Cards", 0);
INSERT INTO `job_type`(`name`, `category`) VALUES ("Receipt Booklet", 3);

INSERT INTO `paper_format`(`length`, `name`, `width`) VALUES (841,"A0",1189);
INSERT INTO `paper_format`(`length`, `name`, `width`) VALUES (594,"A1",841);
INSERT INTO `paper_format`(`length`, `name`, `width`) VALUES (420,"A2",594);
INSERT INTO `paper_format`(`length`, `name`, `width`) VALUES (297,"A3",420);
INSERT INTO `paper_format`(`length`, `name`, `width`) VALUES (210,"A4",297);
INSERT INTO `paper_format`(`length`, `name`, `width`) VALUES (148,"A5",210);
INSERT INTO `paper_format`(`length`, `name`, `width`) VALUES (105,"A6",148);
INSERT INTO `paper_format`(`length`, `name`, `width`) VALUES (74,"A7",105);
INSERT INTO `paper_format`(`length`, `name`, `width`) VALUES (52,"A8",74);


INSERT INTO `paper_grammage`(`value`) VALUES ("45");
INSERT INTO `paper_grammage`(`value`) VALUES ("48");
INSERT INTO `paper_grammage`(`value`) VALUES ("50");
INSERT INTO `paper_grammage`(`value`) VALUES ("53");
INSERT INTO `paper_grammage`(`value`) VALUES ("54");
INSERT INTO `paper_grammage`(`value`) VALUES ("55");
INSERT INTO `paper_grammage`(`value`) VALUES ("60");
INSERT INTO `paper_grammage`(`value`) VALUES ("70");
INSERT INTO `paper_grammage`(`value`) VALUES ("80");
INSERT INTO `paper_grammage`(`value`) VALUES ("90");
INSERT INTO `paper_grammage`(`value`) VALUES ("100");
INSERT INTO `paper_grammage`(`value`) VALUES ("115");
INSERT INTO `paper_grammage`(`value`) VALUES ("120");
INSERT INTO `paper_grammage`(`value`) VALUES ("130");
INSERT INTO `paper_grammage`(`value`) VALUES ("135");
INSERT INTO `paper_grammage`(`value`) VALUES ("140");
INSERT INTO `paper_grammage`(`value`) VALUES ("160");
INSERT INTO `paper_grammage`(`value`) VALUES ("170");
INSERT INTO `paper_grammage`(`value`) VALUES ("180");
INSERT INTO `paper_grammage`(`value`) VALUES ("200");
INSERT INTO `paper_grammage`(`value`) VALUES ("240");
INSERT INTO `paper_grammage`(`value`) VALUES ("250");
INSERT INTO `paper_grammage`(`value`) VALUES ("300");
INSERT INTO `paper_grammage`(`value`) VALUES ("350");
INSERT INTO `paper_grammage`(`value`) VALUES ("400");
INSERT INTO `paper_grammage`(`value`) VALUES ("450");
INSERT INTO `paper_grammage`(`value`) VALUES ("500");

INSERT INTO `paper_type`(`name`) VALUES ("Glazed");
INSERT INTO `paper_type`(`name`) VALUES ("2 Side Glazed");
INSERT INTO `paper_type`(`name`) VALUES ("Offset White");
INSERT INTO `paper_type`(`name`) VALUES ("Duplex");
INSERT INTO `paper_type`(`name`) VALUES ("Book Work opaque");
INSERT INTO `paper_type`(`name`) VALUES ("Newsprint");
INSERT INTO `paper_type`(`name`) VALUES ("Bristol");
INSERT INTO `paper_type`(`name`) VALUES ("CB White");
INSERT INTO `paper_type`(`name`) VALUES ("CFB Piwk");
INSERT INTO `paper_type`(`name`) VALUES ("Matte Paper");
INSERT INTO `paper_type`(`name`) VALUES ("Bond Paper");
INSERT INTO `paper_type`(`name`) VALUES ("Chromolux Paper");
INSERT INTO `paper_type`(`name`) VALUES ("Glossy Paper");
INSERT INTO `paper_type`(`name`) VALUES ("Recycled Paper");
INSERT INTO `paper_type`(`name`) VALUES ("Cardstock");
INSERT INTO `paper_type`(`name`) VALUES ("Coated Paper");
INSERT INTO `paper_type`(`name`) VALUES ("Uncoated Paper");
INSERT INTO `paper_type`(`name`) VALUES ("Toile");
INSERT INTO `paper_type`(`name`) VALUES ("Offset coloured");
INSERT INTO `paper_type`(`name`) VALUES ("Offset green");
INSERT INTO `paper_type`(`name`) VALUES ("Bank");
INSERT INTO `paper_type`(`name`) VALUES ("Art");
INSERT INTO `paper_type`(`name`) VALUES ("Satine white");
INSERT INTO `paper_type`(`name`) VALUES ("Satine green");
INSERT INTO `paper_type`(`name`) VALUES ("Journal, bleu");
INSERT INTO `paper_type`(`name`) VALUES ("Ecriture couleur");
INSERT INTO `paper_type`(`name`) VALUES ("CF");

INSERT INTO `print_type` (`id`, `name`, `abreviation`) VALUES
(1, 'Bkw', 'BWK'),
(2, 'Scr', 'SCRN'),
(3, 'Poly', 'POLY'),
(4, 'Tint', 'Tint'),
(5, 'Panton', 'Panton'),
(6, 'Poly(T<=20)', 'POLY20'),
(7, 'Poly(T>20)', 'POLY20+'),
(8, 'Scrn(T<=20)', 'SCRN20'),
(9, 'Scrn(T>20)', 'SCRN20+');

INSERT INTO `printing_machine` ( `creation_date`, `active`, `name`, `plate_length`, `plate_width`, `thumbnail`, `abbreviation`) VALUES
('2024-05-06 00:00:00.000000', 1, 'GTO', 520, 360, NULL, 'GTO'),
('2024-05-15 07:53:12.897000', 1, 'SPEED MASTER 2 UNITS', 520, 720, NULL, 'SPM2'),
('2024-05-16 09:56:57.543000', 1, 'SORM', 520, 740, NULL, 'SO'),
('2024-05-15 07:53:12.897000', 1, 'SPEED MASTER 5 UNITS', 740, 1020, NULL, 'SPM5'),
('2024-05-15 07:53:12.897000', 0, 'SPEED MASTER 1 UNIT', 740, 1020, NULL, 'SPM1');


INSERT INTO `job_status`(`name`,`description`) VALUES
 ("Draft","Job with Incomplete Information"),
 ("Registered","Job with Complete Information"),
 ("Confrimed","Job that had Complete information and has been crossed check by another managing staff or Marketting Manager"),
 ("Approved","A job that has been Confirmed and also been Approved by  Marketting Manager"),
 ("Abort","A Job that has been Aborted");
