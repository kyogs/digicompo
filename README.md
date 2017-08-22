# Custom component and Common function
  This library is used to set custom font for Textview, Button, Exittext and Checkbox. Also included reusable functions and
  common alert dialog.

# Features
  * Set font for Textview, Button, Exittext and Checkbox by writing just online in XML.
  * Common methods.
  * Alert dialog in which we can set color of buttton and textview.
  * Progress dialog.
  
#  Usage
  * Define Texview as show in blow example.
   **Font/Roboto_Medium.ttf** is Roboto Medium Font file which placed in *assets/Font* directory.

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

*  Progress dialog used for block intraction with UI for some Task.

    For show dialog write below code
```java   
    Dialog dialog = Utils.showProgress(this, false)
```
      For hide dialog use below code
      
```java
    Utils.hideProgress(dialog)
```
     For dismiss on back button Utils.showProgress(this, true)
     
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
    


