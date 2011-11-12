/*
 * HorizontalListView.java v1.5
 *
 * 
 * The MIT License
 * Copyright (c) 2011 Paul Soucy (paul@dev-smart.com)
 * 
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 *
 */

package org.catdroid.encaixat.android.shop.view;
import java.util.LinkedList;
import java.util.Queue;

import android.content.Context;
import android.database.DataSetObserver;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.GestureDetector.OnGestureListener;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.Scroller;

public class HorizontalListView extends AdapterView<ListAdapter>
{

        public boolean alwaysOverrideTouch = true;
        protected ListAdapter adapter;
        private int leftViewIndex = -1;
        private int rightViewIndex = 0;
        protected int currentX;
        protected int nextX;
        private int maxX = Integer.MAX_VALUE;
        private int displayOffset = 0;
        protected Scroller scroller;
        private GestureDetector gesture;
        private Queue<View> removedViewQueue = new LinkedList<View>();
        private OnItemSelectedListener onItemSelectedListener;
        private OnItemClickListener onItemClickedListener;
        private boolean isChanged = false;

        public HorizontalListView(Context context, AttributeSet attrs)
        {
                super(context, attrs);
                if (!isInEditMode()) initView();
        }

        public HorizontalListView(Context context)
        {
                super(context);
                if (!isInEditMode()) initView();
        }

        private synchronized void initView()
        {
                leftViewIndex = -1;
                rightViewIndex = 0;
                displayOffset = 0;
                currentX = 0;
                nextX = 0;
                maxX = Integer.MAX_VALUE;
                scroller = new Scroller(getContext());
                gesture = new GestureDetector(getContext(), mOnGesture);
        }

        @Override
        public void setOnItemSelectedListener(AdapterView.OnItemSelectedListener listener)
        {
                onItemSelectedListener = listener;
        }

        @Override
        public void setOnItemClickListener(AdapterView.OnItemClickListener listener)
        {
                onItemClickedListener = listener;
        }

        private DataSetObserver mDataObserver = new DataSetObserver()
        {

                @Override
                public void onChanged()
                {
                        synchronized (HorizontalListView.this)
                        {
                                isChanged = true;
                        }
                        invalidate();
                        requestLayout();
                }

                @Override
                public void onInvalidated()
                {
                        reset();
                        invalidate();
                        requestLayout();
                }

        };

        @Override
        public ListAdapter getAdapter()
        {
                return adapter;
        }

        @Override
        public View getSelectedView()
        {
                // TODO: implement
                return null;
        }

        @Override
        public void setAdapter(ListAdapter adapter)
        {
                if (this.adapter != null)
                {
                        // adapter.unregisterDataSetObserver(mDataObserver);
                }
                if (this.adapter==null || !this.adapter.equals(adapter)  )
                {
                         this.adapter = adapter;
                        this.adapter.registerDataSetObserver(mDataObserver);
                        reset();
                }
                
        }

        private synchronized void reset()
        {
                initView();
                removeAllViewsInLayout();
                requestLayout();
        }

        @Override
        public void setSelection(int position)
        {
                // TODO: implement
        }

        private void addAndMeasureChild(final View child, int viewPos)
        {
                LayoutParams params = child.getLayoutParams();
                if (params == null)
                {
                        params = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
                }

                addViewInLayout(child, viewPos, params, true);
                child.measure(MeasureSpec.makeMeasureSpec(getWidth(), MeasureSpec.AT_MOST),
                                MeasureSpec.makeMeasureSpec(getHeight(), MeasureSpec.AT_MOST));
        }

        @Override
        protected synchronized void onLayout(boolean changed, int left, int top, int right, int bottom)
        {
                super.onLayout(changed, left, top, right, bottom);

                if (adapter == null)
                {
                        return;
                }

                if (isChanged)
                {
                        int oldCurrentX = currentX;
                        initView();
                        removeAllViewsInLayout();
                        nextX = oldCurrentX;
                        isChanged = false;
                }

                if (scroller.computeScrollOffset())
                {
                        int scrollx = scroller.getCurrX();
                        nextX = scrollx;
                }

                if (nextX < 0)
                {
                        nextX = 0;
                        scroller.forceFinished(true);
                }
                if (nextX > maxX)
                {
                        nextX = maxX;
                        scroller.forceFinished(true);
                }

                int dx = currentX - nextX;

                removeNonVisibleItems(dx);
                fillList(dx);
                positionItems(dx);

                currentX = nextX;

                if (!scroller.isFinished())
                {
                        post(new Runnable()
                        {
                                public void run()
                                {
                                        requestLayout();
                                }
                        });

                }
        }

        private void fillList(final int dx)
        {
                int edge = 0;
                View child = getChildAt(getChildCount() - 1);
                if (child != null)
                {
                        edge = child.getRight();
                }
                fillListRight(edge, dx);

                edge = 0;
                child = getChildAt(0);
                if (child != null)
                {
                        edge = child.getLeft();
                }
                fillListLeft(edge, dx);

        }

        private void fillListRight(int rightEdge, final int dx)
        {
                while (rightEdge + dx < getWidth() && rightViewIndex < adapter.getCount())
                {

                        View child = adapter.getView(rightViewIndex, removedViewQueue.poll(), this);
                        addAndMeasureChild(child, -1);
                        rightEdge += child.getMeasuredWidth();

                        if (rightViewIndex == adapter.getCount() - 1)
                        {
                                maxX = currentX + rightEdge - getWidth();
                        }
                        if (maxX < 0)
                                maxX = 0;
                        rightViewIndex++;
                }

        }

        private void fillListLeft(int leftEdge, final int dx)
        {
                while (leftEdge + dx > 0 && leftViewIndex >= 0)
                {
                        View child = adapter.getView(leftViewIndex, removedViewQueue.poll(), this);
                        addAndMeasureChild(child, 0);
                        leftEdge -= child.getMeasuredWidth();
                        leftViewIndex--;
                        displayOffset -= child.getMeasuredWidth();
                }
        }

        private void removeNonVisibleItems(final int dx)
        {
                View child = getChildAt(0);
                while (child != null && child.getRight() + dx <= 0)
                {
                        displayOffset += child.getMeasuredWidth();
                        removedViewQueue.offer(child);
                        removeViewInLayout(child);
                        leftViewIndex++;
                        child = getChildAt(0);

                }

                child = getChildAt(getChildCount() - 1);
                while (child != null && child.getLeft() + dx >= getWidth())
                {
                        removedViewQueue.offer(child);
                        removeViewInLayout(child);
                        rightViewIndex--;
                        child = getChildAt(getChildCount() - 1);
                }
        }

        private void positionItems(final int dx)
        {
                if (getChildCount() > 0)
                {
                        displayOffset += dx;
                        int left = displayOffset;
                        for (int i = 0; i < getChildCount(); i++)
                        {
                                View child = getChildAt(i);
                                int childWidth = child.getMeasuredWidth();
                                child.layout(left, 0, left + childWidth, child.getMeasuredHeight());
                                left += childWidth;
                        }
                }
        }

        public synchronized void scrollTo(int x)
        {
                scroller.startScroll(nextX, 0, x - nextX, 0);
                requestLayout();
        }

        @Override
        public boolean dispatchTouchEvent(MotionEvent ev)
        {
                boolean handled = gesture.onTouchEvent(ev);
                return handled;
        }

        protected boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY)
        {
                synchronized (HorizontalListView.this)
                {
                        scroller.fling(nextX, 0, (int) -velocityX, 0, 0, maxX, 0, 0);
                }
                requestLayout();

                return true;
        }

        protected boolean onDown(MotionEvent e)
        {
                scroller.forceFinished(true);
                return true;
        }

        private OnGestureListener mOnGesture = new GestureDetector.SimpleOnGestureListener()
        {

                @Override
                public boolean onDown(MotionEvent e)
                {
                        return HorizontalListView.this.onDown(e);
                }

                @Override
                public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY)
                {
                        return HorizontalListView.this.onFling(e1, e2, velocityX, velocityY);
                }

                @Override
                public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY)
                {

                        synchronized (HorizontalListView.this)
                        {
                                nextX += (int) distanceX;
                        }
                        requestLayout();

                        return true;
                }

                @Override
                public boolean onSingleTapConfirmed(MotionEvent e)
                {
                        Rect viewRect = new Rect();
                        for (int i = 0; i < getChildCount(); i++)
                        {
                                View child = getChildAt(i);
                                int left = child.getLeft();
                                int right = child.getRight();
                                int top = child.getTop();
                                int bottom = child.getBottom();
                                viewRect.set(left, top, right, bottom);
                                if (viewRect.contains((int) e.getX(), (int) e.getY()))
                                {
                                        if (onItemClickedListener != null)
                                        {
                                                onItemClickedListener.onItemClick(HorizontalListView.this, child, leftViewIndex + 1 + i,
                                                                adapter.getItemId(leftViewIndex + 1 + i));
                                        }
                                        if (onItemSelectedListener != null)
                                        {
                                                onItemSelectedListener.onItemSelected(HorizontalListView.this, child, leftViewIndex + 1 + i,
                                                                adapter.getItemId(leftViewIndex + 1 + i));
                                        }
                                        break;
                                }

                        }
                        return true;
                }

        };

}