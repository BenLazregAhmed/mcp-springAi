import { Injectable } from '@angular/core';
import { Marked } from 'marked';

@Injectable({
  providedIn: 'root',
})
export class MarkedService {
  private marked: Marked;

  constructor() {
    this.marked = new Marked();
  }

  parse(markdown: string): string {
    return this.marked.parse(markdown) as string;
  }
}
