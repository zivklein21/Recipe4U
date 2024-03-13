package com.cc.recipe4u.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.cc.recipe4u.DataClass.Comment
import com.cc.recipe4u.R
import com.cc.recipe4u.ViewModels.UsersViewModel
import com.squareup.picasso.Picasso

class CommentAdapter(private val comments:List<Comment>):
    RecyclerView.Adapter<CommentAdapter.CommentViewHolder>() {

    class CommentViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val userName: TextView = itemView.findViewById(com.cc.recipe4u.R.id.userName)
        val commentText: TextView = itemView.findViewById(com.cc.recipe4u.R.id.commentText)
        val commentTime:TextView = itemView.findViewById(com.cc.recipe4u.R.id.commentTime)
        val userImageView: ImageView = itemView.findViewById(com.cc.recipe4u.R.id.userImageView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(com.cc.recipe4u.R.layout.row_comments, parent, false)
        return CommentViewHolder(view)
    }

    override fun getItemCount(): Int {
        return comments.size
    }

    override fun onBindViewHolder(holder: CommentViewHolder, position: Int) {
        val comment = comments[position]

        // Bind data to views
        holder.commentTime.text = comment.timestamp.toString()
        holder.commentText.text = comment.comment

        UsersViewModel().getUserById(comment.ownerId) { user ->
            holder.userName.text = user.name
            if (user.photoUrl != "null") {
                Picasso.get()
                    .load(user.photoUrl)
                    .placeholder(R.drawable.progress_animation)
                    .into(holder.userImageView)
            } else {
                holder.userImageView.setImageResource(R.drawable.baseline_person_24)
            }
        }
    }
}

