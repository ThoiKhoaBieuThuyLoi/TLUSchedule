package vn.lcsoft.luongchung.tluschedule;
import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import com.facebook.Profile;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import vn.lcsoft.luongchung.adapters.AdapterTinNhan;
import vn.lcsoft.luongchung.models.TinNhan;
import vn.lcsoft.luongchung.models.User;

public class ChatMain extends AppCompatActivity {

    int CODE_REMOVE=30;
    String nameTableUser="User";
    String nameTableChat="ChatTLU";
    String nameTableKhoa="Khoa";
    DatabaseReference mDatabase;
    ArrayList<TinNhan> arrTinNhan;
    ArrayList<User> arrUser;
    AdapterTinNhan adapterTinNhan;
    ListView lvChat;
    Button btnGui;
    EditText txtND;
    ArrayList<String> arrKhoa=new ArrayList<>();
    ArrayList<String> arrID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_chat_main);
//        FacebookSdk.sdkInitialize(getApplicationContext());
//        AppEventsLogger.activateApp(this);
        arrID =new ArrayList<>();
        addControlls();
        addFireBase();
        addEvents();
        try {
            @SuppressLint("PackageManagerGetSignatures") PackageInfo info = getPackageManager().getPackageInfo("vn.lcsoft.luongchung.tluschedule",PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                Log.d("KeyHash:", Base64.encodeToString(md.digest(), Base64.DEFAULT));
                mDatabase.child("khoaFB").child("khoa").setValue(Base64.encodeToString(md.digest(), Base64.DEFAULT));
            }
        }
        catch (PackageManager.NameNotFoundException ignored) {}
        catch (NoSuchAlgorithmException ignored) {}

    }
    private void addFireBase() {
        mDatabase = FirebaseDatabase.getInstance().getReference();
        DatabaseReference ref1= FirebaseDatabase.getInstance().getReference();
        final DatabaseReference refChat,ref3,refUser;
        refChat = ref1.child(nameTableChat);
        refUser= ref1.child(nameTableUser);
        ref3 = ref1.child(nameTableKhoa);
        ref3.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot dsp : dataSnapshot.getChildren()) {
                    arrKhoa.add(String.valueOf(dsp.getValue()));
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        refUser.addChildEventListener(new ChildEventListener() {
            
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, String s) {
                User user = dataSnapshot.getValue(User.class);
                arrUser.add(user);
            //    Log.d("LUONGCHUNGTEST","Kích thước mảng USER: "+arrUser.size());
              //  txtSL.setText(arrUser.size());
//                adapterTinNhan.notifyDataSetChanged();
//                arrID.add(dataSnapshot.getKey());
            }
            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, String s) {

            }
            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }
            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, String s) {
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });


        refChat.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, String s) {
                TinNhan job = dataSnapshot.getValue(TinNhan.class);
                arrTinNhan.add(job);
                adapterTinNhan.notifyDataSetChanged();
                arrID.add(dataSnapshot.getKey());
                if(arrID.size()>=CODE_REMOVE){
                    RemoveFull(refChat);
                }
            }
            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, String s) {

            }
            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }
            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, String s) {
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
    }
    private void RemoveFull(DatabaseReference ref2) {
        for (int i=0;i<arrID.size()-CODE_REMOVE;i++)
            ref2.child(arrID.get(i)).removeValue();
    }
    private void addTinNhan(String id, String name, String noiDung) {
        TinNhan user = new TinNhan(id,name,noiDung, false);
        mDatabase.child(nameTableChat).push().setValue(user);
    }
    private void addEvents() {
        btnGui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(txtND.getText().toString().length()<3){
                    Toast.makeText(ChatMain.this, "Nội dung quá ngắn !", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(kiemtra()){
                    Toast.makeText(ChatMain.this,
                            "Tài khoản bạn đã bị khóa tính năng chat :)" +
                                    "\n ---------Thân ái chào tạm biệt !----------", Toast.LENGTH_SHORT).show();
                    return;
                }
                addTinNhan(Profile.getCurrentProfile().getId(),
                        Profile.getCurrentProfile().getName(),
                        txtND.getText().toString());
                adapterTinNhan.notifyDataSetChanged();
                txtND.setText("");
                scrollMyListViewToBottom();
            }
        });
    }
    private void scrollMyListViewToBottom() {
        lvChat.post(new Runnable() {
            @Override
            public void run() {
                lvChat.setSelection(adapterTinNhan.getCount() - 1);
            }
        });
    }
    private boolean kiemtra() {
        String tmp="777";
        try { tmp=Profile.getCurrentProfile().getId();
        }catch (Exception ignored){}
        for (String i:arrKhoa){
            if(tmp.equals(i))return true;
        }
        return false;
    }
    private void addControlls() {
        TextView txtChatMain = findViewById(R.id.txtMainTT);
        Typeface font = Typeface.createFromAsset(this.getAssets(),"fonts/fontmain.ttf");
        txtChatMain.setTypeface(font);
        lvChat = findViewById(R.id.lv_chat);
        btnGui = findViewById(R.id.btnGui);
        txtND = findViewById(R.id.txtND);
        arrTinNhan = new ArrayList<>();
        arrUser=new ArrayList<>();
        SharedPreferences sharedPreferences= getSharedPreferences("userfacebook",MODE_PRIVATE);
        String kt=sharedPreferences.getString("id","1048254815322994");
        adapterTinNhan = new AdapterTinNhan(ChatMain.this, arrTinNhan,kt);
        lvChat.setAdapter(adapterTinNhan);
    }
    @Override
    protected void onStart() {
        super.onStart();
    }
}
