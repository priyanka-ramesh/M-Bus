package com.ogs.m_bus;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.text.Editable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;


/**
 * A simple {@link Fragment} subclass.
 */
public class busticket extends Fragment {

    private FirebaseDatabase db;
    Button proceed;
    FirebaseAuth auth123;
    FirebaseDatabase fbdb;
    FirebaseUser user;
    DatabaseReference dbref;
    String name;
    int flag=0;int result;
    String money1;

    Spinner spinner;
    String [] values =
            {"Select trip type","One way trip","Round Trip"};

    EditText input_name,input_from,input_to,seats;
    String[] bus_type = new String[100];
    String[] bus_t_rate = new String[100];
    String[] stages = new String[100];
    String[] Destinationarray = new String[100];
    String[] viaarray = new String[100];
    String[] b_price = new String[100];
    String routenos;
    String busno;
    String depature;
    String Typesbusp;
    int pos_to, pos_from;
    String text;
    String s_name, s_from, s_to;
    //to check from and to in list
    ArrayList<String> routeno = new ArrayList<>();
    ArrayList<String> start = new ArrayList<>();
    ArrayList<String> Destination1 = new ArrayList<>();
    //String[] Destination1=new String[100];
//    ArrayList<String> Destination1 = new ArrayList<>();
    ArrayList<String> via1 = new ArrayList<>();
    ArrayList<String> Types1 = new ArrayList<>();
    ArrayList<String> Rates1 = new ArrayList<>();
    ArrayList<String> Stage = new ArrayList<>();
    ArrayList<String> price = new ArrayList<>();
    // String[] via1 = new String[100];
    int count = 0,  total, total_price;
    public busticket() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v=inflater.inflate(R.layout.fragment_busticket, container, false);
        proceed=(Button)v.findViewById(R.id.btn_proceed_bp);
        db=FirebaseDatabase.getInstance("https://mbus-6b4dd.firebaseio.com/");
        input_name=(EditText)v.findViewById(R.id.input_name);
        input_from=(EditText)v.findViewById(R.id.input_from);
        input_to=(EditText)v.findViewById(R.id.input_to);
        seats=(EditText)v.findViewById(R.id.seats);
        spinner = (Spinner)v.findViewById(R.id.spinner1);
         ArrayAdapter<String> adapter = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_spinner_item, values);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                int position = spinner.getSelectedItemPosition();
    //            Toast.makeText(busticket.this.getContext(),"You have selected "+values[+i],Toast.LENGTH_LONG).show();
                Log.d("message ",values[+i]);
                text=values[+i];

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        proceed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Editable e = input_from.getText();
                String string123 = "";
                if (e != null) {
                    string123 = e.toString();
                }
                Log.d("String value", "String" + string123);
                Editable e1 = input_to.getText();
                String string1 = "";
                if (e1 != null) {
                    string1 = e1.toString();
                }
                Log.d("String value", "String to" + string1);
                total = jsonparse(string123, string1);
               if(total!=0) {
                   int cost = calculate_price(total,Integer.parseInt(seats.getText().toString()));
                   int costr = cost * 2;

                   String s = String.valueOf(cost);
                   String s1 = String.valueOf(costr);
                   uinfo u = new uinfo();
                   uinfo u1 = new uinfo();

                   if (text.equalsIgnoreCase(values[1])) {
                       DatabaseReference dr = db.getReference();
                       u.setName(input_name.getText().toString());
                       u.setFrom(input_from.getText().toString());
                       u.setTo(input_to.getText().toString());
                       u.setSeats(seats.getText().toString());
                       u.setTime(text.toString());
                       u.setPrice(s.toString());
                       dr.child("details").child("Route").push().setValue(u);
                       DatabaseReference t = db.getReference();
                       t.push().setValue(u);
                       fetchmoney(cost);

                   } else if (text.equalsIgnoreCase(values[2])) {
                       DatabaseReference dr = db.getReference();
                       u.setName(input_name.getText().toString());
                       u.setFrom(input_from.getText().toString());
                       u.setTo(input_to.getText().toString());
                       u.setSeats(seats.getText().toString());
                       u.setTime(text.toString());
                       u.setPrice(s1.toString());
                       dr.child("details").child("Route").push().setValue(u);
                       DatabaseReference t = db.getReference();
                       t.push().setValue(u);
                       DatabaseReference dr1 = db.getReference();
                       u1.setName(input_name.getText().toString());
                       u1.setFrom(input_to.getText().toString());
                       u1.setTo(input_from.getText().toString());
                       u1.setSeats(seats.getText().toString());
                       u1.setTime(text.toString());
                       u1.setPrice(s1.toString());
                       dr1.child("details").child("Route").push().setValue(u1);
                       DatabaseReference t1 = db.getReference();
                       t1.push().setValue(u);
                       fetchmoney(costr);

                   } else {
                       Toast.makeText(busticket.this.getContext(), "Please select one type of travel", Toast.LENGTH_SHORT);
                   }


               }
            else{
                Toast.makeText(busticket.this.getContext(),"No Such routes are available",Toast.LENGTH_SHORT).show();
            }
        }

            private int calculate_price(int totalcount,int seats) {
                Log.d("Count:","Count"+totalcount);
                try {
                    for (int p = 1; p <= stages.length; p++) {
                        if (totalcount == p) {

                            total_price = totalcount * Integer.parseInt(bus_t_rate[p]);
                        }
                    }
                    Log.d("tag", "Total price" + total_price);

                } catch (Exception e) {
                    e.printStackTrace();
                }
                return total_price;  }



        });
        return v;
    }


//----------------------
private void fetchmoney(final int cost2) {

    int flag=0;
    final String[] strresult = new String[1];
    auth123 = FirebaseAuth.getInstance();
    fbdb = FirebaseDatabase.getInstance("https://mbus-6b4dd.firebaseio.com/");
    user = FirebaseAuth.getInstance().getCurrentUser();
    dbref = fbdb.getInstance().getReference();
    DatabaseReference ref = dbref.child("Users");
    Users usersinfo1 = new Users();
    name = usersinfo1.getName();
    ref.addValueEventListener(new ValueEventListener() {
        @Override
        public void onDataChange(DataSnapshot dataSnapshot) {
            final String strres;
            Iterator<DataSnapshot> dataSnapshotsChat = dataSnapshot.getChildren().iterator();
            Users usersinfo = new Users();

            while (dataSnapshotsChat.hasNext()) {
                DataSnapshot dataSnapshotChild = dataSnapshotsChat.next();

                usersinfo = dataSnapshotChild.getValue(Users.class);

                money1=usersinfo.getMoney();
                Log.d("Value", "Money : " + usersinfo.getMoney());
                Log.d("Value", "Email : " + usersinfo.getEmail());
                Log.d("Value", "Name : " + usersinfo.getName());
                Log.d("Value", "password : " + usersinfo.getPassword());
                Log.d("Value", "phone number : " + usersinfo.getPhone());
            }
            result = Integer.parseInt(String.valueOf(money1));

            Log.d("messge:","wallet:"+result);
            Log.d("FetchUserMoney:","money "+result);
            int result1 = result - cost2;
            Log.d("FetchUserMoney:","Result1 "+result1);

            strres = Integer.toString(result1);
            Log.d("FetchUserMoney:","String result "+strres);
            strresult[0] =strres;
            usersinfo.setMoney(strres);
          //  dbref.child("Users").push().setValue(usersinfo);
            Toast.makeText(busticket.this.getContext(),"Ticket amount is "+cost2,Toast.LENGTH_LONG).show();

            payment pay= new payment();
            FragmentManager manager =getFragmentManager();
            Bundle bundle = new Bundle();
            String xyz=Integer.toString(cost2);
            Log.d("message","cost "+xyz);
            bundle.putString("Money",xyz);
            pay.setArguments(bundle);
            manager.beginTransaction().replace(R.id.relativelayout_frag,pay,pay.getTag()).commit();

        }
        @Override

        public void onCancelled(DatabaseError databaseError) {
            //  Toast.makeText(buspass.this.getContext(),"Money is  not deduccted from wwallet",Toast.LENGTH_SHORT).show();

            Log.d("messsge:","Money is  not deducted from wallet");}
    });
//    usersinfo1.setMoney(strres);
    //  dbref.child("Users").push().setValue(usersinfo1);
    //flag=1;
    //return strresult[0];

}


    //-----------------------
    private String loadJSONFromAsset() {
        String json = null;
        try {
            InputStream is;
            is = getContext().getAssets().open("bus_routes.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }
    int jsonparse(String from, String to) {

        s_from = from;
        s_to = to;
        int[] a=new int[100];
        Log.d("String value", "String from " + s_from);
        Log.d("String value", "String to" + s_to);
        try {
            // get JSONObject from JSON file
            JSONObject obj = new JSONObject(loadJSONFromAsset());
            JSONArray Types = obj.getJSONArray("Types");

            for (int i = 0; i < Types.length(); i++) {
                JSONObject typesobj = Types.getJSONObject(i);
                bus_type[i] = typesobj.getString("name").toString();
                bus_t_rate[i] = typesobj.getString("rate").toString();
                Types1.add(Types.getString(i));
                Log.d("Types array:", Types.getString(i));
            }
            Log.d("tag", "Types array:" + Types1);
            JSONArray Rates = obj.getJSONArray("Rates");

            for (int i = 0; i < Rates.length(); i++) {
                JSONObject stage = Rates.getJSONObject(i);
                Log.d("Rates array:", Rates.getString(i));
                stages[i] = stage.getString("Stage");
                Stage.add(stage.getString("Stage"));
                bus_t_rate[i] = stage.getString("price");
                price.add(stage.getString("price"));
                for (int j = 0; j < Rates.length(); j++) {
                    Rates1.add(Rates.getString(j));
                }
            }
// Log.d("tag","Rates array:"+Rates1);
//            Log.d("tag","Stage array:"+Stage);
//            Log.d("tag","Rates array:"+price);

            JSONArray userArray = obj.getJSONArray("Routes");

            for (int i = 0; i < userArray.length(); i++) {
                JSONObject userDetail = userArray.getJSONObject(i);
                // fetch email and name and store it in arraylist
                routeno.add(userDetail.getString("route no"));
                routenos = routeno.get(i).toString();
                start.add(userDetail.getString("depature"));
                busno = userDetail.getString("bus no").toString();
                Typesbusp = userDetail.getString("Types").toString();
                depature = (userDetail.getString("depature")).toString();
                JSONArray Destination = userDetail.getJSONArray("Destination");
                Log.d("Route no : ", routeno.get(i));

                for (int j = 0; j < Destination.length(); j++) {
                    Destinationarray[j] = Destination.getString(j).toString();
                    Log.d("Destination array:", Destination.getString(j));
                    Destination1.add(Destination.getString(j));
                    Log.d("tag", "Destination array:" + Destination1);
                }
                JSONArray via = userDetail.getJSONArray("via");
                for (int k = 0; k < via.length(); k++) {

                    via1.add(via.getString(k));
                    viaarray[k] = via.getString(k).toString();


                    Log.d("Via route :", via.getString(k));

                }

                via1.add(Destination1.get(i));
                Log.d("Via : ", "Via array:" + via1);
                //------------------
                for (int q = 0; q < viaarray.length; q++) {
                    if (s_from.equalsIgnoreCase(viaarray[q])) {
                        pos_from = q;
                        Log.d("Tag", "From loc" + s_from + "position:" + pos_from);
                    }
                    if (s_to.equalsIgnoreCase(viaarray[q])) {
                        pos_to = q;
                        Log.d("Tag", "To loc" + s_to + "position:" + pos_to);
                    }
                }
                count =   pos_to-pos_from;
                Log.d("Count:","Count"+count);

                a[i]=count;
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Log.d("Count:","Count"+a[0]);
        return a[0]; }

}
