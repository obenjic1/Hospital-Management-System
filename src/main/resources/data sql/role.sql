

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
(19, NULL, 'Default role to manage users', 'ROLE_ADD_USER');
(20, NULL, 'Default role to manage jobs', 'ROLE_REGISTER_NEW_JOB');
(21, NULL, 'Default role to manage draft jobs', 'ROLE_EDIT_DRAFT_JOB');
(22, NULL, 'Default role to manage switch jobs to different departement', 'ROLE_MOVE_JOB_TO_DEPARTEMENT');
(23, NULL, 'Default role to accept or abort jobs', 'ROLE_ABORT_DRAFT_OR_JOB');
(24, NULL, 'Default role to confirm jobs', 'ROLE_CONFIRM_JOB');
(25, NULL, 'Default role to approve jobs by the marketing manager', 'ROLE_APPROVE_JOB');
(26, NULL, 'Default role to add forecast delivery date to the jobs by the marketing manager', 'ROLE_SET_FORECAST_DELIVERY_DATE'),
(27, NULL, 'Default role to apply discount to the estimate by the marketing manager', 'ROLE_APPLY_DISCOUNT'),
(28, NULL, 'Default role to generate an invoice by the marketing manager', 'ROLE_GENERATE_INVOICE');


