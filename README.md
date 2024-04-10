This is Matthew. I am going to put a little note here that demonstrates how each of the folders/files work in this system

*every bolded file/directory here exists in the src/assets folder*

**PatientList.txt**
- Lists each patient’s name [FirstnameLastname]

**patients**
- File name: FirstLastname.txt
- Contents of each by line:
    - First Name
    - Last Name
    - Birthday (mm/dd/yyyy)
    - Password

**conversations**
- File Name: FirstLastname.txt
- Contents of each by line:
    - Patient Name
    - Doctor Name
    - [name of sender]: [message] \n

**patientinfo**
- File name: FirstLastname.txt
- Contents of each by line:
    - Sex
    - Address
    - Phone number
    - Email
    - Guardian name
    - Guardian email
    - Guardian phone number
    - Emergency contact name
    - Emergency contact phone number
    - Insurance info
    - Pharmacy Name
    - Pharmacy Address

**appointments**
- File name: FirstLastname.txt
- Contents of each by line:
    - Date (mm/dd/yyyy)
    - height (float)
    - weight (float)
    - temp   (float)
    - blood pressure
    - new prescriptions
    - exam results
    - recommendations
    - Every line afterwards until an empty line/EOF will be visit summary.
