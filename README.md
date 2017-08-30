# Custom component and Common function
  This library is used to set custom font for Textview, Button, EditText and Checkbox. It also includes reusable functions and
  common alert dialog.

# Features
  * Set font for Textview, Button, EditText and Checkbox by writing just one line in XML.
  * Common methods.
  * Alert dialog in which we can set color of Button and TextView.
  * Progress dialog.
  
   Add it in your root build.gradle at the end of repositories:
```java 
  allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
```
   Add the dependency:
```java 
  compile 'com.github.kyogs:digicompo:v1.7'
```

#  Usage
  * Define TextView as shown in below example.
   **Font/Roboto_Medium.ttf** is Roboto Medium Font file which is placed in *assets/Font* directory.

```XML
   <com.digi.TextView
        android:id="@+id/txt"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_marginTop="10dp"
        android:text="Roboto Medium Font"
        android:textSize="10sp"
        app:font_path="Font/Roboto_Medium.ttf" />
```

*  Progress dialog used for preventing user interaction (meanwhile) with UI.

    To show dialog, write below code:
```java   
    Dialog dialog = Utils.showProgress(this, isCancelable)
```
      To hide dialog, use below code:
      
```java
    Utils.hideProgress(dialog)
```
     
 * How to show alert dialog?
 
 ```java 
     private void showAlertDialog() {
        DialogInterface.OnClickListener listener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which) {
                    case DialogInterface.BUTTON_POSITIVE:
                        // do task
                        dialog.dismiss();
                        break;
                    case DialogInterface.BUTTON_NEGATIVE:
                        dialog.dismiss();
                        break;
                }
            }
        };
        Utils.showAlert(this, "Title", "Message", "YES", "NO", listener, isCancelable, isAnimation);
    }
 ```  
    


