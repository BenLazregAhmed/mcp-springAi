import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { MarkedService } from '../marked.service';

@Component({
  selector: 'app-chat',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './chat.component.html',
  styleUrls: ['./chat.component.css'],
})
export class ChatComponent implements OnInit {
  userMessage: string = '';
  agentResponse: string = '';
  isLoading: boolean = false;

  constructor(private http: HttpClient, private markedService: MarkedService) {}

  ngOnInit(): void {}

  async sendMessage() {
    if (!this.userMessage.trim()) {
      return;
    }

    this.isLoading = true;
    const messageToSend = this.userMessage;
    this.userMessage = ''; // Clear input immediately

    try {
      const response: any = await this.http
          .get(`http://localhost:8066/ask?message=${messageToSend}`, { responseType: 'text' })
          .toPromise();
      this.agentResponse = this.markedService.parse(response);
    } catch (error) {
      console.error('Error sending message:', error);
      this.agentResponse = this.markedService.parse('Error: Could not get a response from the agent. Please try again.');
    } finally {
      this.isLoading = false;
    }
  }
}
