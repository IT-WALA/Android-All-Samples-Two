/*
 * Copyright (C) 2016 Nishant Srivastava
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package ghanekar.vaibhav.allsamples2.fragments.recyclerview.moveitem;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.Collections;
import java.util.List;

import ghanekar.vaibhav.allsamples2.R;
import ghanekar.vaibhav.allsamples2.fragments.recyclerview.ModelRecyclerView;


public class RvAdapterMoveItem extends RecyclerView.Adapter<RvAdapterMoveItem.ItemViewHolder>
        implements RVHAdapter {

    public class ItemViewHolder extends RecyclerView.ViewHolder implements RVHViewHolder {

        final TextView txt;

        public ItemViewHolder(View itemView) {
            super(itemView);
            txt = itemView.findViewById(R.id.txt1);
        }

        @Override
        public void onItemClear() {
            System.out.println("Item is unselected");
        }

        @Override
        public void onItemSelected(int actionstate) {
            System.out.println("Item is selected");
        }
    }

    private final List<ModelRecyclerView> dataList;

    public RvAdapterMoveItem(List<ModelRecyclerView> dataList) {
        this.dataList = dataList;
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    @Override
    public void onBindViewHolder(ItemViewHolder holder, int position) {
        holder.txt.setText(dataList.get(position).getName());
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_list_item, parent, false);

        return new ItemViewHolder(view);
    }

    @Override
    public void onItemDismiss(int position, int direction) {
        remove(position);
    }

    @Override
    public boolean onItemMove(int fromPosition, int toPosition) {
        swap(fromPosition, toPosition);
        return false;
    }

    private void remove(int position) {
        dataList.remove(position);
        notifyItemRemoved(position);
    }

    private void swap(int firstPosition, int secondPosition) {
        Collections.swap(dataList, firstPosition, secondPosition);
        notifyItemMoved(firstPosition, secondPosition);
    }
}