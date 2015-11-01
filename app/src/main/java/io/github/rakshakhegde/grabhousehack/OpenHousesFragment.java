package io.github.rakshakhegde.grabhousehack;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.drivemode.android.typeface.TypefaceHelper;
import com.facebook.drawee.view.SimpleDraweeView;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.List;

/**
 * Created by Rakshak.R.Hegde on 11/1/2015.
 */
public class OpenHousesFragment extends Fragment {
	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		RecyclerView recyclerView = (RecyclerView) inflater.inflate(R.layout.fragment_openhouse, container, false);
		setupRecyclerView(recyclerView);
		supplyDataToRecyclerView(recyclerView);
		return recyclerView;
	}

	private static void setupRecyclerView(RecyclerView recyclerView) {
		recyclerView.setHasFixedSize(true);
		recyclerView.setLayoutManager(new LinearLayoutManager(recyclerView.getContext()));
	}

	private void supplyDataToRecyclerView(final RecyclerView recyclerView) {
		new ParseQuery<ParseObject>("House")
				.orderByAscending("date")
				.findInBackground(new FindCallback<ParseObject>() {
					@Override
					public void done(List<ParseObject> openHousePOJOs, ParseException e) {
						recyclerView.setAdapter(new OpenHousesRecyclerViewAdapter(openHousePOJOs));
					}
				});
	}

	public class OpenHousesRecyclerViewAdapter extends RecyclerView.Adapter<OpenHousesViewHolder> {
		private List<ParseObject> openHousePOJOs;

		public OpenHousesRecyclerViewAdapter(List<ParseObject> openHousePOJOs) {
			this.openHousePOJOs = openHousePOJOs;
		}

		@Override
		public OpenHousesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
			View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.openhouse_card, parent, false);
			return new OpenHousesViewHolder(v);
		}

		@Override
		public void onBindViewHolder(OpenHousesViewHolder holder, int position) {
			final ParseObject openHousePOJO = openHousePOJOs.get(position);
			// Set Image
			Uri uri = Uri.parse(openHousePOJO.getString("image"));
			holder.openHouse.setImageURI(uri);
			// Set title
			holder.title.setText(openHousePOJO.getString("name"));
			// Set address
			holder.address.setText(openHousePOJO.getString("address"));
			// Set cost
			holder.cost.setText(openHousePOJO.getString("cost"));
			holder.contact.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					final String contactNumber = openHousePOJO.getString("contact");
					new AlertDialog.Builder(getActivity())
							.setTitle("Call?")
							.setMessage("Call " + contactNumber + "?")
							.setNegativeButton("Cancel", null)
							.setPositiveButton("OK", new DialogInterface.OnClickListener() {
								@Override
								public void onClick(DialogInterface dialog, int which) {
									Intent callIntent = new Intent(Intent.ACTION_CALL);
									callIntent.setData(Uri.parse("tel:" + contactNumber));
									startActivity(callIntent);
								}
							})
							.show();
				}
			});
		}

		@Override
		public int getItemCount() {
			return openHousePOJOs.size();
		}
	}

	private class OpenHousesViewHolder extends RecyclerView.ViewHolder {
		SimpleDraweeView openHouse;
		TextView title, address, cost;
		View contact;

		public OpenHousesViewHolder(View itemView) {
			super(itemView);
			TypefaceHelper.getInstance().setTypeface((CardView) itemView, MyApp.FONT_NAME);
			openHouse = (SimpleDraweeView) itemView.findViewById(R.id.image_openhouse);
			title = (TextView) itemView.findViewById(R.id.tv_title);
			address = (TextView) itemView.findViewById(R.id.tv_address);
			cost = (TextView) itemView.findViewById(R.id.tv_cost);
			contact = itemView.findViewById(R.id.image_contact);
		}
	}
}

